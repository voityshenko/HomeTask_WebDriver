package model;

import java.util.Objects;

public class Form {
    private String seriesOfMachine;
    private String gpuType;
    private String numberOfGpus;

    public Form(String seriesOfMachine, String gpuType, String numberOfGpus) {
        this.seriesOfMachine = seriesOfMachine;
        this.gpuType = gpuType;
        this.numberOfGpus = numberOfGpus;
    }

    public String getGpuType() {
        return gpuType;
    }

    public void setGpuType(String gpuType) {
        this.gpuType = gpuType;
    }

    public String getNumberOfGpus() {
        return numberOfGpus;
    }

    public void setNumberOfGpus(String numberOfGpus) {
        this.numberOfGpus = numberOfGpus;
    }

    public String getSeriesOfMachine() {
        return seriesOfMachine;
    }

    public void setSeriesOfMachine(String seriesOfMachine) {
        this.seriesOfMachine = seriesOfMachine;
    }


    @Override
    public String toString() {
        return "Form{" +
                "seriesOfMachine='" + seriesOfMachine + '\'' +
                ", numberOfGpus='" + numberOfGpus + '\'' +
                ", gpuType='" + gpuType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Form)) return false;
        Form form = (Form) o;
        return Objects.equals(getSeriesOfMachine(), form.getSeriesOfMachine()) &&
                Objects.equals(getNumberOfGpus(), form.getNumberOfGpus()) &&
                Objects.equals(getGpuType(), form.getGpuType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGpuType(), getNumberOfGpus(), getSeriesOfMachine());
    }
}
