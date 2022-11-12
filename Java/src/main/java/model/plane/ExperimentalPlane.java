package model.plane;

import type.ClassificationLevelType;
import type.ExperimentalType;

public class ExperimentalPlane extends Plane{

    private ExperimentalType type;
    private ClassificationLevelType classificationLevel;

    public ExperimentalPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity, ExperimentalType type, ClassificationLevelType classificationLevel) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.type = type;
        this.classificationLevel = classificationLevel;
    }

    public ClassificationLevelType getClassificationLevel(){
        return classificationLevel;
    }

    public void setClassificationLevel(ClassificationLevelType classificationLevel){
        this.classificationLevel = classificationLevel;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "experimentalPlane{" +
                "model='" + model + '\'' +
                '}';
    }
}
