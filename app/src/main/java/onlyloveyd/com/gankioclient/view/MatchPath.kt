package onlyloveyd.com.gankioclient.view

import android.util.SparseArray
import java.util.*

/**
 * This code comes from https://github.com/liaohuqiu/android-Ultra-Pull-To-Refresh/
 */
object MatchPath {

    val V_LEFT = '#'
    val H_TOP_BOTTOM = '$'
    val V_RIGHT = '%'
    private val sPointList: SparseArray<FloatArray>
    var isButtonModle: Boolean = false

    init {
        sPointList = SparseArray<FloatArray>()
        val LETTERS = arrayOf(floatArrayOf(
                // A
                24f, 0f, 1f, 22f, 1f, 22f, 1f, 72f, 24f, 0f, 47f, 22f, 47f, 22f, 47f, 72f, 1f, 48f, 47f, 48f),

                floatArrayOf(
                        // B
                        0f, 0f, 0f, 72f, 0f, 0f, 37f, 0f, 37f, 0f, 47f, 11f, 47f, 11f, 47f, 26f, 47f, 26f, 38f, 36f, 38f, 36f, 0f, 36f, 38f, 36f, 47f, 46f, 47f, 46f, 47f, 61f, 47f, 61f, 38f, 71f, 37f, 72f, 0f, 72f),

                floatArrayOf(
                        // C
                        47f, 0f, 0f, 0f, 0f, 0f, 0f, 72f, 0f, 72f, 47f, 72f),

                floatArrayOf(
                        // D
                        0f, 0f, 0f, 72f, 0f, 0f, 24f, 0f, 24f, 0f, 47f, 22f, 47f, 22f, 47f, 48f, 47f, 48f, 23f, 72f, 23f, 72f, 0f, 72f),

                floatArrayOf(
                        // E
                        0f, 0f, 0f, 72f, 0f, 0f, 47f, 0f, 0f, 36f, 37f, 36f, 0f, 72f, 47f, 72f),

                floatArrayOf(
                        // F
                        0f, 0f, 0f, 72f, 0f, 0f, 47f, 0f, 0f, 36f, 37f, 36f),

                floatArrayOf(
                        // G
                        47f, 23f, 47f, 0f, 47f, 0f, 0f, 0f, 0f, 0f, 0f, 72f, 0f, 72f, 47f, 72f, 47f, 72f, 47f, 48f, 47f, 48f, 24f, 48f),

                floatArrayOf(
                        // H
                        0f, 0f, 0f, 72f, 0f, 36f, 47f, 36f, 47f, 0f, 47f, 72f),

                floatArrayOf(
                        // I
                        0f, 0f, 47f, 0f, 24f, 0f, 24f, 72f, 0f, 72f, 47f, 72f),

                floatArrayOf(
                        // J
                        47f, 0f, 47f, 72f, 47f, 72f, 24f, 72f, 24f, 72f, 0f, 48f),

                floatArrayOf(
                        // K
                        0f, 0f, 0f, 72f, 47f, 0f, 3f, 33f, 3f, 38f, 47f, 72f),

                floatArrayOf(
                        // L
                        0f, 0f, 0f, 72f, 0f, 72f, 47f, 72f),

                floatArrayOf(
                        // M
                        0f, 0f, 0f, 72f, 0f, 0f, 24f, 23f, 24f, 23f, 47f, 0f, 47f, 0f, 47f, 72f),

                floatArrayOf(
                        // N
                        0f, 0f, 0f, 72f, 0f, 0f, 47f, 72f, 47f, 72f, 47f, 0f),

                floatArrayOf(
                        // O
                        0f, 0f, 0f, 72f, 0f, 72f, 47f, 72f, 47f, 72f, 47f, 0f, 47f, 0f, 0f, 0f),

                floatArrayOf(
                        // P
                        0f, 0f, 0f, 72f, 0f, 0f, 47f, 0f, 47f, 0f, 47f, 36f, 47f, 36f, 0f, 36f),

                floatArrayOf(
                        // Q
                        0f, 0f, 0f, 72f, 0f, 72f, 23f, 72f, 23f, 72f, 47f, 48f, 47f, 48f, 47f, 0f, 47f, 0f, 0f, 0f, 24f, 28f, 47f, 71f),

                floatArrayOf(
                        // R
                        0f, 0f, 0f, 72f, 0f, 0f, 47f, 0f, 47f, 0f, 47f, 36f, 47f, 36f, 0f, 36f, 0f, 37f, 47f, 72f),

                floatArrayOf(
                        // S
                        47f, 0f, 0f, 0f, 0f, 0f, 0f, 36f, 0f, 36f, 47f, 36f, 47f, 36f, 47f, 72f, 47f, 72f, 0f, 72f),

                floatArrayOf(
                        // T
                        0f, 0f, 47f, 0f, 24f, 0f, 24f, 72f),

                floatArrayOf(
                        // U
                        0f, 0f, 0f, 72f, 0f, 72f, 47f, 72f, 47f, 72f, 47f, 0f),

                floatArrayOf(
                        // V
                        0f, 0f, 24f, 72f, 24f, 72f, 47f, 0f),

                floatArrayOf(
                        // W
                        0f, 0f, 0f, 72f, 0f, 72f, 24f, 49f, 24f, 49f, 47f, 72f, 47f, 72f, 47f, 0f),

                floatArrayOf(
                        // X
                        0f, 0f, 47f, 72f, 47f, 0f, 0f, 72f),

                floatArrayOf(
                        // Y
                        0f, 0f, 24f, 23f, 47f, 0f, 24f, 23f, 24f, 23f, 24f, 72f),

                floatArrayOf(
                        // Z
                        0f, 0f, 47f, 0f, 47f, 0f, 0f, 72f, 0f, 72f, 47f, 72f))
        val NUMBERS = arrayOf(floatArrayOf(
                // 0
                0f, 0f, 0f, 72f, 0f, 72f, 47f, 72f, 47f, 72f, 47f, 0f, 47f, 0f, 0f, 0f), floatArrayOf(
                // 1
                24f, 0f, 24f, 72f),

                floatArrayOf(
                        // 2
                        0f, 0f, 47f, 0f, 47f, 0f, 47f, 36f, 47f, 36f, 0f, 36f, 0f, 36f, 0f, 72f, 0f, 72f, 47f, 72f),

                floatArrayOf(
                        // 3
                        0f, 0f, 47f, 0f, 47f, 0f, 47f, 36f, 47f, 36f, 0f, 36f, 47f, 36f, 47f, 72f, 47f, 72f, 0f, 72f),

                floatArrayOf(
                        // 4
                        0f, 0f, 0f, 36f, 0f, 36f, 47f, 36f, 47f, 0f, 47f, 72f),

                floatArrayOf(
                        // 5
                        0f, 0f, 0f, 36f, 0f, 36f, 47f, 36f, 47f, 36f, 47f, 72f, 47f, 72f, 0f, 72f, 0f, 0f, 47f, 0f),

                floatArrayOf(
                        // 6
                        0f, 0f, 0f, 72f, 0f, 72f, 47f, 72f, 47f, 72f, 47f, 36f, 47f, 36f, 0f, 36f),

                floatArrayOf(
                        // 7
                        0f, 0f, 47f, 0f, 47f, 0f, 47f, 72f),

                floatArrayOf(
                        // 8
                        0f, 0f, 0f, 72f, 0f, 72f, 47f, 72f, 47f, 72f, 47f, 0f, 47f, 0f, 0f, 0f, 0f, 36f, 47f, 36f),

                floatArrayOf(
                        // 9
                        47f, 0f, 0f, 0f, 0f, 0f, 0f, 36f, 0f, 36f, 47f, 36f, 47f, 0f, 47f, 72f))
        // A - Z
        for (i in LETTERS.indices) {
            sPointList.append(i + 65, LETTERS[i])
        }
        // a - z
        for (i in LETTERS.indices) {
            sPointList.append(i + 65 + 32, LETTERS[i])
        }
        // 0 - 9
        for (i in NUMBERS.indices) {
            sPointList.append(i + 48, NUMBERS[i])
        }
        // blank
        addChar(' ', floatArrayOf())
        // -
        addChar('-', floatArrayOf(0f, 36f, 47f, 36f))
        // .
        addChar('.', floatArrayOf(24f, 60f, 24f, 72f))

        //右竖线
        addChar(V_LEFT, floatArrayOf(-12f, 120f, -12f, 38f, -12f, 38f, -12f, -45f))
        //上下横线
        addChar(H_TOP_BOTTOM, floatArrayOf(0f, -45f, 23f, -45f, 23f, -45f, 67f, -45f, 0f, 120f, 23f, 120f, 23f, 120f, 67f, 120f))

        //左竖线
        addChar(V_RIGHT, floatArrayOf(79f, -45f, 79f, 38f, 79f, 38f, 79f, 120f))
    }

    fun addChar(c: Char, points: FloatArray) {
        sPointList.append(c.toInt(), points)
    }

    /**
     * @return ArrayList of float[] {x1, y1, x2, y2}
     */
    @JvmOverloads fun getPath(str: String, scale: Float = 1f, gapBetweenLetter: Int = 14): ArrayList<FloatArray> {
        val list = ArrayList<FloatArray>()
        var offsetForWidth = 0f
        for (i in 0..str.length - 1) {
            val pos = str[i].toInt()
            val key = sPointList.indexOfKey(pos)
            if (key == -1) {
                continue
            }
            var points = sPointList.get(pos)

            if (isButtonModle) {
                val points1 = FloatArray(points.size + 16)
                for (j in 0..sPointList.get(H_TOP_BOTTOM.toInt()).size - 1) {
                    points1[j] = sPointList.get(H_TOP_BOTTOM.toInt())[j]
                }
                for (j in points.indices) {
                    points1[j + 16] = points[j]
                }
                points = points1
            }

            val pointCount = points.size / 4
            for (j in 0..pointCount - 1) {
                val line = FloatArray(4)
                for (k in 0..3) {
                    val l = points[j * 4 + k]
                    // x
                    if (k % 2 == 0) {
                        line[k] = (l + offsetForWidth) * scale
                    } else {
                        line[k] = l * scale
                    }// y
                }
                list.add(line)
            }
            offsetForWidth += (57 + gapBetweenLetter).toFloat()
        }

        if (isButtonModle) {
            isButtonModle = false
        }
        return list
    }
}
