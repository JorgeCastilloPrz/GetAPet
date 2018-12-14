package jorgecastilloprz.github.io.getapet.petgrid.picasso

import android.graphics.*
import androidx.core.graphics.createBitmap
import com.squareup.picasso.Transformation


class Tint(private val sat: Float, private val destinationColor: Int) : Transformation {

    override fun transform(source: Bitmap): Bitmap {
        val r = 0.213f * (1.0f - sat)
        val g = 0.715f * (1.0f - sat)
        val b = 0.072f * (1.0f - sat)
        val desaturatedMatrix = ColorMatrix(
            floatArrayOf(
                r + sat, r, r, 0f, 0f, //
                g, g + sat, g, 0f, 0f, //
                b, b, b + sat, 0f, 0f, //
                0f, 0f, 0f, 0f, 255f
            )//
        )

        val dr = Color.red(destinationColor)
        val dg = Color.green(destinationColor)
        val db = Color.blue(destinationColor)
        val drf = dr / 255f
        val dgf = dg / 255f
        val dbf = db / 255f
        val tintMatrix = ColorMatrix(
            floatArrayOf(
                drf, 0f, 0f, 0f, 0f, //
                0f, dgf, 0f, 0f, 0f, //
                0f, 0f, dbf, 0f, 0f, //
                0f, 0f, 0f, 1f, 0f
            )//
        )

        tintMatrix.preConcat(desaturatedMatrix)
        val filter = ColorMatrixColorFilter(tintMatrix)
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
