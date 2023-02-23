package org.example.readers;

public enum FileExtension {

    TXT("txt"), PDF("pdf"), DOCX("docx");
    private String extension;

    FileExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return this.extension;
    }

    public static FileExtension getFileExtension(String extension) {
        FileExtension result = null;
        for (FileExtension fileExtension : values()) {
            if (fileExtension.getExtension().equals(extension)) {
                result = fileExtension;
                break;
            }
        }
        return result;
    }

}
