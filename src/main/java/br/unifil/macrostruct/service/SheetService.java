package br.unifil.macrostruct.service;

import br.unifil.macrostruct.exception.ValidationException;
import br.unifil.macrostruct.model.FoodEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class SheetService {

    private static final String ERROR_COLUMN_ROW = "A coluna %d da linha %d está com o valor incorreto.";
    private static final String ERROR_FILE_FORMAT = "Formato do arquivo inválido";

    private static final Integer INDEX_DESCRIPTION = 0;
    private static final Integer INDEX_SERVING_SIZE = 1;
    private static final Integer INDEX_CARBOHYDRATES = 2;
    private static final Integer INDEX_PROTEIN = 3;
    private static final Integer INDEX_TOTAL_FAT = 4;
    private static final Integer INDEX_CALORIES = 5;

    public List<FoodEntity> readFromSheet(MultipartFile file) {
        try {
            Sheet sheet = this.getSheet(file, 0);
            return StreamSupport.stream(sheet.spliterator(), false)
                    .skip(1)
                    .map(this.convert)
                    .toList();
        } catch (IOException ex) {
            throw new ValidationException("Erro ao ler planilha, verifique o arquivo e tente novamente.");
        }
    }

    private final Function<Row, FoodEntity> convert = row -> {
        FoodEntity foodEntity = FoodEntity.builder().build();

        foodEntity.setDescription(this.getStringCell(row, INDEX_DESCRIPTION));
        foodEntity.setServingSize(this.getIntCell(row, INDEX_SERVING_SIZE));
        foodEntity.setCarbohydrates(this.getDoubleCell(row, INDEX_CARBOHYDRATES));
        foodEntity.setProtein(this.getDoubleCell(row, INDEX_PROTEIN));
        foodEntity.setTotalFat(this.getDoubleCell(row, INDEX_TOTAL_FAT));
        foodEntity.setCalories(this.getDoubleCell(row, INDEX_CALORIES));

        return foodEntity;
    };

    private Sheet getSheet(MultipartFile file, Integer index) throws IOException {
        Sheet sheet;
        String extension = getFileExtension(file);

        if (extension.equals(".xls")) {
            sheet = new HSSFWorkbook(new POIFSFileSystem(file.getInputStream())).getSheetAt(index);
        } else if (extension.equals(".xlsx")) {
            sheet = new XSSFWorkbook(file.getInputStream()).getSheetAt(index);
        } else {
            throw new ValidationException(ERROR_FILE_FORMAT);
        }
        return sheet;
    }

    private String getFileExtension(MultipartFile file) {
        Optional<String> fileName = Optional.ofNullable(file.getOriginalFilename());
        return fileName.map(f -> f.substring(f.indexOf(".")))
                .orElseThrow(() -> new ValidationException(ERROR_FILE_FORMAT));
    }

    private String getStringCell(Row row, Integer column) {
        var cell = row.getCell(column);
        String cellValue = cell.getStringCellValue();
        if (StringUtils.isEmpty(cellValue)) {
            throw new ValidationException(String.format(ERROR_COLUMN_ROW, column + 1, cell.getRowIndex() + 1));
        }
        return cellValue;
    }

    private Integer getIntCell(Row row, Integer column) {
        row.getCell(column).setCellType(CellType.STRING);
        var cell = row.getCell(column);
        String cellValue = cell.getStringCellValue();
        try {
            return Integer.valueOf(cellValue);
        } catch (NumberFormatException ex) {
            throw new ValidationException(String.format(ERROR_COLUMN_ROW, column + 1, cell.getRowIndex() + 1));
        }
    }

    private Double getDoubleCell(Row row, Integer column) {
        row.getCell(column).setCellType(CellType.STRING);
        var cell = row.getCell(column);
        String cellValue = cell.getStringCellValue();
        try {
            return Double.valueOf(cellValue);
        } catch (NumberFormatException ex) {
            throw new ValidationException(String.format(ERROR_COLUMN_ROW, column + 1, cell.getRowIndex() + 1));
        }
    }
}
