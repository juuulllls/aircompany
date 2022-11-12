package model.plane;

import type.ClassificationLevelType;
import type.ExperimentalType;

import java.util.Objects;

public class ExperimentalPlane extends Plane{

    private final ExperimentalType experimentalType;
    private ClassificationLevelType classificationLevel;

    public ExperimentalPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity, ExperimentalType experimentalType, ClassificationLevelType classificationLevel) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.experimentalType = experimentalType;
        this.classificationLevel = classificationLevel;
    }

    public ClassificationLevelType getClassificationLevel(){
        return classificationLevel;
    }

    public void setClassificationLevel(ClassificationLevelType classificationLevel){
        this.classificationLevel = classificationLevel;
    }

    public ExperimentalType getExperimentalType() {
        return experimentalType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExperimentalPlane experimentalPlane = (ExperimentalPlane) o;
        return experimentalType == experimentalPlane.experimentalType && classificationLevel == experimentalPlane.classificationLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), experimentalType, classificationLevel);
    }

    @Override
    public String toString() {
        return "experimentalPlane{" +
                "model='" + model + '\'' +
                '}';
    }

}
