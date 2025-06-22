/*
 * Copyright 2025 Thosea (github.com/imthosea)
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS “AS IS”
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package me.thosea.kswing.example

import me.thosea.kswing.components.KButton
import me.thosea.kswing.components.KComboBox
import me.thosea.kswing.components.KFlowPanel
import me.thosea.kswing.components.KGridPanel
import me.thosea.kswing.components.KLabel
import me.thosea.kswing.components.KLazyVBoxPanel
import me.thosea.kswing.components.KPanel
import me.thosea.kswing.components.KRadioButton
import me.thosea.kswing.components.KScrollPane
import me.thosea.kswing.components.KSeparator
import me.thosea.kswing.components.KTextField
import me.thosea.kswing.components.KVBoxPanel
import me.thosea.kswing.components.KVGlue
import me.thosea.kswing.components.custom.KCircleButton
import me.thosea.kswing.components.custom.placeholder.KPlaceholderTextArea
import me.thosea.kswing.components.custom.placeholder.KPlaceholderTextField
import me.thosea.kswing.extensions.kBold
import me.thosea.kswing.extensions.kCapHeightToPreferred
import me.thosea.kswing.extensions.kCapWidth
import me.thosea.kswing.extensions.kFontAttr
import me.thosea.kswing.extensions.kFontSize
import me.thosea.kswing.extensions.kItalic
import me.thosea.kswing.extensions.kRevalidateAndRepaint
import me.thosea.kswing.listeners.kActionListener
import me.thosea.kswing.listeners.kAncestorRemovedListener
import me.thosea.kswing.listeners.kComponentResizedListener
import me.thosea.kswing.listeners.kFocusLostListener
import me.thosea.kswing.listeners.kItemListener
import me.thosea.kswing.listeners.kMouseWheelListener
import me.thosea.kswing.listeners.kWindowClosedListener
import me.thosea.kswing.listeners.kWindowLostFocusListener
import me.thosea.kswing.states.KNonnullState
import me.thosea.kswing.utils.KConstants.kEmptyBorder
import me.thosea.kswing.utils.KConstants.kEmptyColor
import me.thosea.kswing.utils.kBagConstraints
import me.thosea.kswing.utils.kUseStates
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Container
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import java.awt.font.TextAttribute
import java.awt.geom.RoundRectangle2D
import java.time.LocalDate
import java.time.LocalTime
import java.time.Month
import java.time.Year
import java.time.format.DateTimeFormatter
import java.util.*
import javax.swing.BorderFactory
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.ScrollPaneConstants
import javax.swing.SwingConstants
import javax.swing.UIManager
import javax.swing.text.AbstractDocument
import javax.swing.text.AttributeSet
import javax.swing.text.DocumentFilter
import kotlin.system.exitProcess

/**
 * Minimal copy of Apple reminders with KSwing.
 * @author thosea
 */
/*
 * If you're expanding upon this, try:
 *  - adding save functionality (make a Reminder data class)
 *  - adding multiple lists for organization
 *  - actually notifying the user at the date/time they choose
 *
 * For simplicity, each example is in one file, but you should
 * probably split this into multiple files.
*/
object Reminders {
	private val DATE = LocalDate.now()
	private val REMOVAL_TIMER = Timer(/*isDaemon=*/ true)
	private val POPUP_BACKGROUND_COLOR = Color(2, 19, 41)

	private val remindersList = KLazyVBoxPanel()

	fun run() {
		println("Running Reminders")

		val frame = JFrame("KSwing Reminders")
		frame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE

		with(frame) {
			setupBody()
		}

		frame.isResizable = false
		frame.setSize(800, 700)
		frame.setLocationRelativeTo(null)
		frame.isVisible = true
		frame.rootPane.requestFocus()
	}

	private fun JFrame.setupBody() {
		KVBoxPanel(addConstraints = BorderLayout.PAGE_START) {
			KLabel("My Reminders").kFontSize(24f).kBold(true)

			val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy")
			KLabel("Today is ${formatter.format(DATE)}").kFontSize(18f).kItalic(true)
		}

		with(remindersList) {
			KPanel { makeReminderPanel("Wash the dishes") }
			KPanel { makeReminderPanel("Eat chocolate") }
			KPanel { makeReminderPanel("Eat more chocolate") }
		}

		KScrollPane(
			addConstraints = BorderLayout.CENTER,
			horizontalPolicy = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER,
			view = remindersList
		).border = kEmptyBorder

		KFlowPanel(addConstraints = BorderLayout.PAGE_END) {
			KButton("Quit").kActionListener { exitProcess(0) }
			KButton("+").kActionListener {
				with(remindersList) {
					KPanel { makeReminderPanel() }
					kRevalidateAndRepaint()
				}
			}
		}
	}

	// what the HELL is wrong with swing
	// im done, if you resize the window that's your problem
	private fun hope(component: Container) {
		component.revalidate()
		component.repaint()

		for(sub in component.components) {
			if(sub is Container)
				hope(sub)
		}
	}

	class ReminderTime {
		companion object {
			val TOMORROW = DATE.plusDays(1)
			val TIME = LocalTime.now()
			val IS_LEAP_YEAR = Year.from(TOMORROW).isLeap
		}

		val enabled = KNonnullState(false)
		val month = KNonnullState(TOMORROW.month)
		val day = KNonnullState(TOMORROW.dayOfMonth)

		val hour = KNonnullState((TIME.hour + 1) % 13)
		val minute = KNonnullState(TIME.minute)

		val am = KNonnullState(TIME.hour < 12)

		init {
			month.listen {
				val length = it.length(IS_LEAP_YEAR)
				if(day.value >= length) day.value = length
			}
		}
	}

	private fun JPanel.makeReminderPanel(initialText: String? = null) {
		val time = ReminderTime()
		layout = GridBagLayout()

		KSeparator(addConstraints = kBagConstraints(
			gridx = 0, gridy = 0,
			gridwidth = 4,
			fill = GridBagConstraints.HORIZONTAL,
			anchor = GridBagConstraints.PAGE_START,
			weightx = 1.0
		))

		val reminderField = KPlaceholderTextArea(
			text = initialText,
			placeholderText = "Reminder",
			addConstraints = kBagConstraints(
				gridx = 1, gridy = 1,
				fill = GridBagConstraints.HORIZONTAL,
				insets = Insets(0, 10, 0, 0),
				weightx = 0.8
			)) {
			background = kEmptyColor
			border = kEmptyBorder
			lineWrap = true
			isOpaque = false

			kComponentResizedListener {
				// this is also called when the component is initially added
				this@makeReminderPanel.kCapHeightToPreferred()
				this@makeReminderPanel.kRevalidateAndRepaint()
			}

			onShowPlaceholder {
				kItalic = true
				foreground = Color.GRAY
			}
			onHidePlaceholder {
				kItalic = false
				foreground = Color.WHITE
			}
		}

		KRadioButton(addConstraints = kBagConstraints(
			gridx = 0, gridy = 1,
			insets = Insets(0, 20, 0, 0),
			weightx = 0.1
		)) {
			var removeTask: TimerTask? = null

			kItemListener {
				if(isSelected) {
					reminderField.kFontAttr(TextAttribute.STRIKETHROUGH to TextAttribute.STRIKETHROUGH_ON)
					reminderField.kItalic = true
					removeTask = object: TimerTask() {
						override fun run() {
							remindersList.remove(this@makeReminderPanel)
							remindersList.kRevalidateAndRepaint()
						}
					}
					REMOVAL_TIMER.schedule(removeTask, 1000L)
				} else {
					removeTask?.cancel()
					reminderField.kFontAttr(TextAttribute.STRIKETHROUGH to null)
					reminderField.kItalic = false
				}
			}
		}

		KPlaceholderTextField(
			placeholderText = "Add note...",
			addConstraints = kBagConstraints(
				gridx = 1, gridy = 2,
				fill = GridBagConstraints.HORIZONTAL,
				insets = Insets(0, 10, 0, 0),
				weightx = 0.8
			)) {
			background = kEmptyColor
			border = kEmptyBorder
			horizontalAlignment = SwingConstants.LEADING
			kFontSize = 13f
			isOpaque = false

			onShowPlaceholder {
				kItalic = true
				foreground = Color.GRAY
			}
			onHidePlaceholder {
				kItalic = false
				foreground = Color.WHITE
			}
		}

		KLabel(addConstraints = kBagConstraints(
			gridx = 1, gridy = 3,
			fill = GridBagConstraints.HORIZONTAL,
			insets = Insets(0, 10, 0, 0),
			weightx = 0.8
		)) {
			background = kEmptyColor
			border = kEmptyBorder
			horizontalAlignment = SwingConstants.LEADING
			kFontSize = 13f
			isOpaque = false

			kUseStates(time.enabled, time.month, time.day, time.hour, time.minute, time.am) {
				text = if(time.enabled.value) {
					// sorry europeans
					"${time.month.value.value}/${time.day.value}" +
							" at " +
							"${time.hour.value}:${time.minute.value} " +
							if(time.am.value) "AM" else "PM"
				} else {
					"No date or time"
				}
			}
		}

		KCircleButton(
			icon = UIManager.getIcon("TabbedPane.closeIcon"),
			addConstraints = kBagConstraints(
				gridx = 2, gridy = 1,
				ipadx = -1, ipady = -1,
				insets = Insets(0, 0, 0, 1),
				weightx = 0.1
			)) {
			foreground = Color.RED
			kActionListener {
				remindersList.remove(this@makeReminderPanel)
				remindersList.kRevalidateAndRepaint()
			}
		}

		KCircleButton(
			icon = UIManager.getIcon("OptionPane.informationIcon"),
			addConstraints = kBagConstraints(
				gridx = 3, gridy = 1,
				ipadx = -1, ipady = -1,
				insets = Insets(0, 10, 0, 10),
				weightx = 0.1
			)) {
			background = kEmptyColor
			border = kEmptyBorder
			kActionListener { showPopuo(this, time) }
		}
	}

	private var lastPopupTime: Long = 0

	private fun showPopuo(
		button: JButton,
		time: ReminderTime,
	) {
		if((System.currentTimeMillis() - lastPopupTime) < 100) return

		time.enabled.value = true

		val popup = JFrame("KSwing Reminder Popup")

		popup.kWindowClosedListener { lastPopupTime = System.currentTimeMillis() }
		popup.kWindowLostFocusListener { popup.dispose() }

		with(popup) {
			setupPopup(time)
		}

		popup.setLocationRelativeTo(button)
		popup.isVisible = true
	}

	private fun JFrame.setupPopup(time: ReminderTime) {
		isUndecorated = true
		size = Dimension(260, 320)
		shape = RoundRectangle2D.Double(0.0, 0.0, 260.0, 320.0, 30.0, 30.0)

		contentPane.background = POPUP_BACKGROUND_COLOR
		contentPane.layout = BoxLayout(contentPane, BoxLayout.Y_AXIS)

		KLabel("Set Date and Time")

		KVBoxPanel(addConstraints = BorderLayout.CENTER) {
			background = POPUP_BACKGROUND_COLOR

			KComboBox(arrayOf(
				"January", "Febuary", "Mars",
				"Aprin", "Man", "Jungle",
				"Julie", "Augment", "Septembre",
				"Spookymonth", "None", "Descend"
			)) {
				selectedIndex = time.month.value.ordinal
				kActionListener {
					time.month.value = Month.of(selectedIndex + 1)
				}
			}

			KGridPanel(0, 7) {
				KLabel("Sun").kFontSize(12f)
				KLabel("Mon").kFontSize(12f)
				KLabel("Tue").kFontSize(12f)
				KLabel("Wed").kFontSize(12f)
				KLabel("Thu").kFontSize(12f)
				KLabel("Fri").kFontSize(12f)
				KLabel("Sat").kFontSize(12f)
			}

			KGridPanel(0, 7) {
				background = POPUP_BACKGROUND_COLOR

				time.month.use { month ->
					removeAll()

					for(i in 1..month.length(ReminderTime.IS_LEAP_YEAR)) {
						KButton("$i") {
							kFontSize = 20f
							border = kEmptyBorder

							val listener = time.day.use { day ->
								if(day == i) {
									border = BorderFactory.createRaisedBevelBorder()
								} else {
									border = kEmptyBorder
									isSelected = false
								}
							}
							kAncestorRemovedListener { time.day.removeListener(listener) }
							kActionListener { time.day.value = i }
						}
					}

					kRevalidateAndRepaint()
				}
			}
		}

		KFlowPanel(addConstraints = BorderLayout.PAGE_END) {
			background = POPUP_BACKGROUND_COLOR

			timeInputBox(1, 12, time.hour)
			KLabel(":")
			timeInputBox(0, 59, time.minute)

			KComboBox(arrayOf("AM", "PM")) {
				selectedIndex = if(time.am.value) 0 else 1
				kCapWidth(89)
				kActionListener { time.am.value = selectedIndex == 0 }
			}
		}

		KButton("Remove Time").kActionListener {
			time.enabled.value = false
			dispose()
		}

		KVGlue()
	}

	private fun JPanel.timeInputBox(min: Int, max: Int, state: KNonnullState<Int>) {
		KTextField {
			fun applyText() {
				state.value = text?.toIntOrNull()?.coerceIn(min, max) ?: 1
			}

			kFocusLostListener { applyText() }
			kMouseWheelListener {
				applyText()
				val value = state.value - (it.scrollAmount * it.wheelRotation)
				state.value = value.coerceIn(min, max)
			}

			state.use { text = if(it < 10) "0$it" else "$it" }
			(document as AbstractDocument).documentFilter = makeFilter(2)
			kCapWidth(60)
		}
	}

	private fun makeFilter(maxLength: Int): DocumentFilter {
		return object: DocumentFilter() {
			val regex = Regex("^[0-9]*$") // numbers only

			override fun insertString(fb: FilterBypass, offset: Int, text: String, attr: AttributeSet?) {
				if(text.matches(regex) && (fb.document.length + text.length) <= maxLength) {
					super.insertString(fb, offset, text, attr)
				}
			}

			override fun replace(fb: FilterBypass, offset: Int, length: Int, text: String, attr: AttributeSet?) {
				if(!text.matches(regex))
					return

				val newLength = fb.document.length - length + text.length
				var text = text
				if(newLength > maxLength) {
					val space = newLength - maxLength
					if(space <= 0) return
					text = text.substring(0, text.length - space)
				}

				super.replace(fb, offset, length, text, attr)
			}
		}
	}
}