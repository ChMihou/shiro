package com.example.restful.demo.enums;

public enum ReportFileTypeEnum {
    XLSX(0, ".xlsx"), XLS(1, ".xls");
    int status;
    String statusDesc;

    private ReportFileTypeEnum(int status, String statusDesc) {
        this.status = status;
        this.statusDesc = statusDesc;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public static ReportFileTypeEnum getType(int status) {
        for (ReportFileTypeEnum excelFileTypeEnums : ReportFileTypeEnum.values()) {
            if (excelFileTypeEnums.getStatus() == status) {
                return excelFileTypeEnums;
            }
        }
        return null;
    }

    public static String getStatusDesc(int status) {
        return getType(status).getStatusDesc();
    }
}