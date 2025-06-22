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
import me.thosea.kswing.components.KCardPanel
import me.thosea.kswing.components.KCheckBox
import me.thosea.kswing.components.KComboBox
import me.thosea.kswing.components.KFlowPanel
import me.thosea.kswing.components.KGridBagPanel
import me.thosea.kswing.components.KGridPanel
import me.thosea.kswing.components.KHBox
import me.thosea.kswing.components.KLabel
import me.thosea.kswing.components.KRadioButton
import me.thosea.kswing.components.KSeparator
import me.thosea.kswing.components.KSlider
import me.thosea.kswing.components.KTextField
import me.thosea.kswing.components.KVBox
import me.thosea.kswing.components.KVBoxPanel
import me.thosea.kswing.components.KVGlue
import me.thosea.kswing.components.KVStruct
import me.thosea.kswing.extensions.kBold
import me.thosea.kswing.extensions.kCapSizeToPreferred
import me.thosea.kswing.extensions.kFontAttr
import me.thosea.kswing.extensions.kFontSize
import me.thosea.kswing.extensions.kItalic
import me.thosea.kswing.listeners.kActionListener
import me.thosea.kswing.listeners.kChangeListener
import me.thosea.kswing.listeners.kItemListener
import me.thosea.kswing.states.KNonnullState
import me.thosea.kswing.utils.kBagConstraints
import me.thosea.kswing.utils.kListenStates
import me.thosea.kswing.utils.kUseStates
import java.awt.BorderLayout
import java.awt.CardLayout
import java.awt.GridBagConstraints
import java.awt.Insets
import java.awt.event.ItemEvent
import java.awt.font.TextAttribute
import javax.swing.BorderFactory
import javax.swing.ButtonGroup
import javax.swing.JCheckBox
import javax.swing.JFrame
import javax.swing.JPanel
import kotlin.system.exitProcess

/**
 * Character Creator example with Kswing
 * @author thosea
 */
/*
 * If you're expanding upon this, try:
 *   - adding more species
 *   - adding an "import/export" option
 *
 * For simplicity, each example is in one file, but you should
 * probably split this into multiple files.
*/
object CharacterCreator {
	enum class Species(val friendlyName: String) {
		HUMAN("Human"), DOG("Dog"), CAT("Cat");

		override fun toString() = friendlyName
	}
	/*
	 * notice how dark and mint chocolate aren't listed,
	 * because they aren't real options
	 */
	enum class Chocolates(val friendlyName: String) {
		MILK("Milk"), WHITE("White"), MANDM("M&Ms");

		override fun toString() = friendlyName
	}

	enum class Foods {
		MEATS, DOG_TREATS, MILK;
	}

	private val categoryBorder = BorderFactory.createRaisedBevelBorder()

	private val realness = KNonnullState(1)
	private val species = KNonnullState(Species.HUMAN)
	private val favChocolate = KNonnullState(Chocolates.MILK)
	private val favFood = KNonnullState(Foods.MEATS)
	private val crimeCount = KNonnullState(0)
	private val totalPunishment = KNonnullState(0)

	fun run() {
		println("Running Character Creator")

		val frame = JFrame("KSwing Character Creator")
		frame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE

		with(frame) {
			setupBody()
		}

		frame.setSize(800, 700)
		frame.setLocationRelativeTo(null)
		frame.isVisible = true
		frame.rootPane.requestFocus()
	}

	private fun JFrame.setupBody() {
		KLabel("Make Your Very Own Vessel", addConstraints = BorderLayout.PAGE_START) {
			kFontSize = 24f
			kBold = true
		}
		KFlowPanel(addConstraints = BorderLayout.PAGE_END) {
			// wrap in FlowLayout to center
			KButton("Quit") {
				kActionListener { exitProcess(0) }
			}
		}

		KGridPanel(0, 2, addConstraints = BorderLayout.CENTER) {
			KGridBagPanel { basicsSection() }
			KVBoxPanel { hobbiesSection() }
			KVBoxPanel { foodSection() }
			KVBoxPanel { criminalLifeSection() }
		}
	}

	private fun JPanel.basicsSection() {
		border = categoryBorder

		KLabel("Basics", addConstraints = kBagConstraints(
			gridx = 0, gridy = 0,
			gridwidth = 2,
		)) {
			kFontSize = 22f
			kBold = true
		}

		KLabel("Name: ", addConstraints = kBagConstraints(
			gridx = 0, gridy = 1,
			weightx = 0.5,
			anchor = GridBagConstraints.LINE_END,
			insets = Insets(0, 0, 0, 2),
		))
		KTextField(addConstraints = kBagConstraints(
			gridx = 1, gridy = 1,
			weightx = 0.5,
			anchor = GridBagConstraints.LINE_START,
			insets = Insets(0, 2, 0, 0),
			// the width of the text field is inconsistent across platforms
			ipadx = 20
		))

		KLabel("Species: ", addConstraints = kBagConstraints(
			gridx = 0, gridy = 2,
			weightx = 0.5,
			anchor = GridBagConstraints.LINE_END,
			insets = Insets(0, 0, 0, 2)
		))
		KComboBox(Species.entries.toTypedArray(), addConstraints = kBagConstraints(
			gridx = 1, gridy = 2,
			weightx = 0.5,
			anchor = GridBagConstraints.LINE_START,
			insets = Insets(0, 2, 0, 0)
		)) {
			kActionListener { species.value = selectedItem as Species }
		}

		KLabel(addConstraints = kBagConstraints(
			gridx = 0, gridy = 3,
			gridwidth = 2,
		)) {
			kUseStates(species, realness) {
				val sliderName = when(species.value) {
					Species.HUMAN -> "Realness"
					Species.DOG -> "Cuteness"
					Species.CAT -> "Purring"
				}
				text = "$sliderName level: ${realness.value}"
			}
		}

		KSlider(min = 1, max = 3, value = 1, addConstraints = kBagConstraints(
			gridx = 0, gridy = 4,
			gridwidth = 2,
		)) {
			kChangeListener { realness.value = value }
			paintTicks = true
			paintLabels = true
			majorTickSpacing = 1
		}

		// push all components to the top
		KVStruct(0, addConstraints = kBagConstraints(
			gridx = 0, gridy = 5,
			weighty = 1.0
		))
	}

	private fun JPanel.hobbiesSection() {
		border = categoryBorder
		KLabel("Activities").kFontSize(22f).kBold(true)

		KLabel("Sleep Level:") {
			kFontAttr(TextAttribute.UNDERLINE to TextAttribute.UNDERLINE_ON)
		}

		val group = ButtonGroup()
		KRadioButton("Little to None", buttonGroup = group) {
			isSelected = true
		}
		KRadioButton("Low", buttonGroup = group)
		KRadioButton("Medium / Average", buttonGroup = group)
		KRadioButton("Long Sleeper", buttonGroup = group)
		KRadioButton("UNLIMITED SLEEPING", buttonGroup = group) {
			toolTipText = "Only available on cats"
			species.listen { isEnabled = it == Species.CAT }
			species.listen {
				if(it != Species.CAT && isSelected) {
					group.setSelected(group.elements.nextElement().model, true)
				}
			}
		}
//		KVStruct(5)
		KVGlue()
		KSeparator {
//			kCapHeight(20)
		}
		KVGlue()
		KCheckBox("Dreams about food") {
			species.listen {
				if(it == Species.CAT) {
					isSelected = true
					isEnabled = false
					toolTipText = "Of course you dream about food!"
				} else if(!isEnabled) {
					isEnabled = true
					isSelected = false
					toolTipText = null
				}
			}
		}

	}

	private fun JPanel.foodSection() {
		border = categoryBorder
		KLabel("Foods").kFontSize(22f).kBold(true)

		KHBox {
			KLabel("Favorite Chocolate: ")
			KComboBox(Chocolates.entries.toTypedArray()) {
				kActionListener { favChocolate.value = selectedItem as Chocolates }
				species.listen { isEnabled = it == Species.HUMAN }
			}
			kCapSizeToPreferred()
		}

		KVBox {
			val comment = KLabel {
				kFontSize = 14f
				kItalic = true
			}

			val group = ButtonGroup()
			KLabel("Favorite Food (from the list):", addIndex = 0) {
				kFontAttr(TextAttribute.UNDERLINE to TextAttribute.UNDERLINE_ON)
			}
			KRadioButton("Meats and chicken", buttonGroup = group, addIndex = 1) {
				isSelected = true
				kUseStates(species, favFood) {
					if(isSelected) comment.text = when(species.value) {
						Species.HUMAN -> "Solid choice."
						Species.DOG -> "Too much chewing!"
						Species.CAT -> "Too much chewing..."
					}
				}
				kActionListener { favFood.value = Foods.MEATS }
			}
			KRadioButton("Dog treats", buttonGroup = group, addIndex = 2) {
				kListenStates(species, favFood) {
					if(isSelected) comment.text = when(species.value) {
						Species.HUMAN -> "What's wrong with you?"
						Species.DOG -> "Yummy!!"
						Species.CAT -> "This isn't for me!"
					}
				}
				kActionListener { favFood.value = Foods.DOG_TREATS }
			}
			KRadioButton("Milk", buttonGroup = group, addIndex = 3) {
				kListenStates(species, favFood) {
					if(isSelected) comment.text = when(species.value) {
						Species.HUMAN -> "Calcium and whatnot"
						Species.DOG -> "This isn't food..."
						Species.CAT -> "I'll have it."
					}
				}
				kActionListener { favFood.value = Foods.MILK }
			}
		}

	}

	private fun JPanel.criminalLifeSection() {
		border = categoryBorder
		KLabel("Criminal Life").kFontSize(22f).kBold(true)

		fun JCheckBox.setup(punishment: Int) {
			kItemListener {
				if(it.id != ItemEvent.ITEM_STATE_CHANGED) return@kItemListener

				// usually you could just go with a different listener for less code,
				// but we're dealing with number-sensitive stuff here
				when(it.stateChange) {
					ItemEvent.SELECTED -> {
						crimeCount.value++
						totalPunishment.value += punishment
					}

					ItemEvent.DESELECTED -> {
						crimeCount.value--
						totalPunishment.value -= punishment
					}
				}
			}
			crimeCount.listen { isEnabled = it < 3 || isSelected }
			species.listen { isSelected = false } // clear selections
			kFontSize = 14f
		}

		KLabel("Favorite Crimes (Pick 3)") {
			kFontAttr(TextAttribute.UNDERLINE to TextAttribute.UNDERLINE_ON)
		}
		KCardPanel {
			KFlowPanel(addConstraints = Species.HUMAN.name) {
				KCheckBox("Eating all the chips").setup(50)
				KCheckBox("Staying up past bedtime").setup(3)
				KCheckBox("Calling 711 for pizza").setup(3)
				KCheckBox("Downloading viruses on the computer").setup(16)
				KCheckBox("Messy room").setup(14)
			}
			KFlowPanel(addConstraints = Species.DOG.name) {
				KCheckBox("Peeing on the sofa").setup(3)
				KCheckBox("Stealing all the food").setup(6)
				KCheckBox("Playing with fire").setup(15)
				KCheckBox("Attacking the neighbors").setup(6)
				KCheckBox("Barking too loud").setup(6)
			}
			KFlowPanel(addConstraints = Species.CAT.name) {
				KCheckBox("Scratching the couch").setup(15)
				KCheckBox("Didn't land on paws").setup(30) // the ultimate clickbait
				KCheckBox("Puking on the carpet").setup(1)
				KCheckBox("Drinking from the toilet").setup(20)
				KCheckBox("Too cute").setup(100)
			}

			species.use { (layout as CardLayout).show(this, it.name) }
		}

		KLabel {
			kUseStates(species, totalPunishment) {
				val type = when(species.value) {
					Species.HUMAN -> "stern lectures"
					Species.DOG -> "strange looks"
					Species.CAT -> "viral videos"
				}
				text = "Sentence: ${totalPunishment.value} $type"
			}
		}
	}
}