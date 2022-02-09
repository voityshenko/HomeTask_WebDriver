package model;

import lombok.Data;

@Data
public class Form {
    private String seriesOfMachine;
    private String gpuType;
    private String numberOfGpus;

    public Form(String seriesOfMachine, String gpuType, String numberOfGpus) {
        this.seriesOfMachine = seriesOfMachine;
        this.gpuType = gpuType;
        this.numberOfGpus = numberOfGpus;
    }


//    @Override
//    public String toString() {
//        return "Form{" +
//                "seriesOfMachine='" + seriesOfMachine + '\'' +
//                ", numberOfGpus='" + numberOfGpus + '\'' +
//                ", gpuType='" + gpuType + '\'' +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Form)) return false;
//        Form form = (Form) o;
//        return Objects.equals(getSeriesOfMachine(), form.getSeriesOfMachine()) &&
//                Objects.equals(getNumberOfGpus(), form.getNumberOfGpus()) &&
//                Objects.equals(getGpuType(), form.getGpuType());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getGpuType(), getNumberOfGpus(), getSeriesOfMachine());
//    }
}
