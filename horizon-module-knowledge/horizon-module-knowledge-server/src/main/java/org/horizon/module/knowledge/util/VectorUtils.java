package org.horizon.module.knowledge.util;

import java.util.List;

public class VectorUtils {

    private VectorUtils() {}

    /**
     * List<Float> -> float[]，并校验 null/NaN/Inf
     */
    public static float[] toFloatArrayStrict(List<Float> vec) {
        if (vec == null || vec.isEmpty()) {
            throw new IllegalArgumentException("Empty embedding");
        }
        float[] arr = new float[vec.size()];
        for (int j = 0; j < vec.size(); j++) {
            Float v = vec.get(j);
            if (v == null || !Float.isFinite(v)) {
                throw new IllegalArgumentException("Embedding contains null/NaN/Infinity at index " + j);
            }
            arr[j] = v;
        }
        return arr;
    }
}
