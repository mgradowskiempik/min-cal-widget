// Copyright (c) 2016, Miquel Martí <miquelmarti111@gmail.com>
// See LICENSE for licensing information
package cat.mvmike.minimalcalendarwidget.domain.configuration.item

import android.content.Context
import cat.mvmike.minimalcalendarwidget.R
import java.time.DayOfWeek

enum class Theme(
    val displayString: Int,
    val mainLayout: Int,
    val mainBackground: Int,
    private val cellHeader: Cell,
    private val cellHeaderSaturday: Cell,
    private val cellHeaderSunday: Cell,
    private val cellDay: Cell,
    private val cellDayThisMonth: Cell,
    private val cellDaySaturdayThisMonth: Cell,
    private val cellDaySundayThisMonth: Cell,
    private val cellDayToday: Cell,
    private val cellDaySaturdayToday: Cell,
    private val cellDaySundayToday: Cell
) {
    DARK(
        displayString = R.string.dark,
        mainLayout = R.layout.widget_dark,
        mainBackground = R.color.background_full_dark,
        cellHeader = Cell(
            layout = R.layout.dark_cell_header
        ),
        cellHeaderSaturday = Cell(
            layout = R.layout.dark_cell_header,
            background = R.color.background_saturday_this_month_dark
        ),
        cellHeaderSunday = Cell(
            layout = R.layout.dark_cell_header,
            background = R.color.background_sunday_this_month_dark
        ),
        cellDay = Cell(layout = R.layout.dark_cell_day),
        cellDayThisMonth = Cell(
            layout = R.layout.dark_cell_day_this_month,
            background = R.color.background_this_month_dark
        ),
        cellDaySaturdayThisMonth = Cell(
            layout = R.layout.dark_cell_day_this_month,
            background = R.color.background_saturday_this_month_dark
        ),
        cellDaySundayThisMonth = Cell(
            layout = R.layout.dark_cell_day_this_month,
            background = R.color.background_sunday_this_month_dark
        ),
        cellDayToday = Cell(
            layout = R.layout.dark_cell_day_today,
            background = R.color.background_today_dark
        ),
        cellDaySaturdayToday = Cell(
            layout = R.layout.dark_cell_day_today,
            background = R.color.background_saturday_today_dark
        ),
        cellDaySundayToday = Cell(
            layout = R.layout.dark_cell_day_today,
            background = R.color.background_sunday_today_dark
        )
    ),
    LIGHT(
        displayString = R.string.light,
        mainLayout = R.layout.widget_light,
        mainBackground = R.color.background_full_light,
        cellHeader = Cell(
            layout = R.layout.light_cell_header
        ),
        cellHeaderSaturday = Cell(
            layout = R.layout.light_cell_header,
            background = R.color.background_saturday_this_month_light
        ),
        cellHeaderSunday = Cell(
            layout = R.layout.light_cell_header,
            background = R.color.background_sunday_this_month_light
        ),
        cellDay = Cell(layout = R.layout.light_cell_day),
        cellDayThisMonth = Cell(
            layout = R.layout.light_cell_day_this_month,
            background = R.color.background_this_month_light
        ),
        cellDaySaturdayThisMonth = Cell(
            layout = R.layout.light_cell_day_this_month,
            background = R.color.background_saturday_this_month_light
        ),
        cellDaySundayThisMonth = Cell(
            layout = R.layout.light_cell_day_this_month,
            background = R.color.background_sunday_this_month_light
        ),
        cellDayToday = Cell(
            layout = R.layout.light_cell_day_today,
            background = R.color.background_today_light
        ),
        cellDaySaturdayToday = Cell(
            layout = R.layout.light_cell_day_today,
            background = R.color.background_saturday_today_light
        ),
        cellDaySundayToday = Cell(
            layout = R.layout.light_cell_day_today,
            background = R.color.background_sunday_today_light
        )
    );

    fun getCellHeader(dayOfWeek: DayOfWeek): Cell {
        return when (dayOfWeek) {
            DayOfWeek.SATURDAY -> cellHeaderSaturday
            DayOfWeek.SUNDAY -> cellHeaderSunday
            else -> cellHeader
        }
    }

    fun getCellDay(isToday: Boolean, inMonth: Boolean, dayOfWeek: DayOfWeek): Cell {
        if (isToday) {
            return when (dayOfWeek) {
                DayOfWeek.SATURDAY -> cellDaySaturdayToday
                DayOfWeek.SUNDAY -> cellDaySundayToday
                else -> cellDayToday
            }
        }

        if (inMonth) {
            return when (dayOfWeek) {
                DayOfWeek.SATURDAY -> cellDaySaturdayThisMonth
                DayOfWeek.SUNDAY -> cellDaySundayThisMonth
                else -> cellDayThisMonth
            }
        }

        return cellDay
    }
}

fun getThemeDisplayValues(context: Context) =
    Theme.values().map { theme ->
        context.getString(theme.displayString).replaceFirstChar { it.uppercase() }
    }.toTypedArray()

data class Cell(
    val id: Int = android.R.id.text1,
    val layout: Int,
    val background: Int? = null
)
