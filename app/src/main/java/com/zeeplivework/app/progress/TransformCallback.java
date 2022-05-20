package com.zeeplivework.app.progress;

import android.graphics.Matrix;
import android.graphics.RectF;

public interface TransformCallback {
    void getRootBounds(RectF rectF);

    void getTransform(Matrix matrix);
}