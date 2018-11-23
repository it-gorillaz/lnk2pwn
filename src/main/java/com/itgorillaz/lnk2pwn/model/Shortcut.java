package com.itgorillaz.lnk2pwn.model;

public class Shortcut {

    private String targetPath;
    private String workingDir;
    private String arguments;
    private String description;
    private String fileName;
    private String fakeExtension;
    private String iconLocation;
    private Integer iconIndex;
    private String windowStyle;
    private String vbsFileName;
    private String command;
    private String commandOuput;
    private String outputPath;

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

    public String getWorkingDir() {
        return workingDir;
    }

    public void setWorkingDir(String workingDir) {
        this.workingDir = workingDir;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFakeExtension() {
        return fakeExtension;
    }

    public void setFakeExtension(String fakeExtension) {
        this.fakeExtension = fakeExtension;
    }

    public String getIconLocation() {
        return iconLocation;
    }

    public void setIconLocation(String iconPath) {
        this.iconLocation = iconPath;
    }

    public Integer getIconIndex() {
        return iconIndex;
    }

    public void setIconIndex(Integer iconIndex) {
        this.iconIndex = iconIndex;
    }

    public String getWindowStyle() {
        return windowStyle;
    }

    public void setWindowStyle(String windowStyle) {
        this.windowStyle = windowStyle;
    }

    public String getVbsFileName() {
        return vbsFileName;
    }

    public void setVbsFileName(String vbsFileName) {
        this.vbsFileName = vbsFileName;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommandOuput() {
        return commandOuput;
    }

    public void setCommandOuput(String commandOuput) {
        this.commandOuput = commandOuput;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

}
