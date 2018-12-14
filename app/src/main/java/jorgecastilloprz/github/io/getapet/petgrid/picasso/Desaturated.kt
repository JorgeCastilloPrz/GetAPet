package jorgecastilloprz.github.io.getapet.petgrid.picasso

import android.graphics.*
import androidx.core.graphics.createBitmap
import com.squareup.picasso.Transformation


class Desaturated : Transformation {
    override fun transform(source: Bitmap): Bitmap {
        val lr = 0.2126f
        val lg = 0.7152f
        val lb = 0.0722f
        val matrix = ColorMatrix(
            floatArrayOf(
                lr, lg, lb, 0f, 0f, //
                lr, lg, lb, 0f, 0f, //
                lr, lg, lb, 0f, 0f, //
                0f, 0f, 0f, 0f, 255f
            )//
        )
        val filter = ColorMatrixColorFilter(matrix)
        val paint = Paint()
        paint.colorFilter = filter

        val width = source.width
        val height = source.height
        val destination = createBitmap(width, height)
        val canvas = Canvas(destination)
        canvas.drawBitmap(source, 0f, 0f, paint)
        source.recycle()
        return destination
    }

    override fun key(): String {
        return "grayscale()"
    }
}
