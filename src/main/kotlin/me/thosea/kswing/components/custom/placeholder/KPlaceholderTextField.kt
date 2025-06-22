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

@file:OptIn(ExperimentalContracts::class)
@file:Suppress("FunctionName", "Unused")

package me.thosea.kswing.components.custom.placeholder

import me.thosea.kswing.listeners.kAncestorAddedListener
import me.thosea.kswing.listeners.kFocusListener
import me.thosea.kswing.other.FullyOpen
import java.awt.Component
import java.awt.Container
import java.awt.event.FocusEvent
import java.awt.event.FocusListener
import javax.swing.JTextField
import javax.swing.SwingConstants
import javax.swing.text.Document
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * [KPlaceholderTextFieldImpl] and adds it to the [Container] you call it on
 *
 * This is a custom KSwing component.
 */
inline fun Container.KPlaceholderTextField(
	doc: Document? = null,
	text: String? = null, columns: Int = 0,
	placeholderText: String? = null,
	addConstraints: Any? = null, addIndex: Int = -1, setup: KPlaceholderTextFieldImpl.() -> Unit = {},
): KPlaceholderTextFieldImpl {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val textField = KPlaceholderTextFieldImpl(doc, text, columns, placeholderText).apply {
		alignmentX = Component.CENTER_ALIGNMENT
		horizontalAlignment = SwingConstants.CENTER
	}
	setup.invoke(textField)
	add(textField, addConstraints, addIndex)
	return textField
}

/**
 * [KPlaceholderTextFieldImpl]
 *
 * This is a custom KSwing component.
 */
inline fun KLazyPlaceholderTextField(
	doc: Document? = null,
	text: String? = null, columns: Int = 0,
	placeholderText: String? = null,
	setup: KPlaceholderTextFieldImpl.() -> Unit = {},
): KPlaceholderTextFieldImpl {
	contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
	val textField = KPlaceholderTextFieldImpl(doc, text, columns, placeholderText).apply {
		alignmentX = Component.CENTER_ALIGNMENT
		horizontalAlignment = SwingConstants.CENTER
	}
	setup.invoke(textField)
	return textField
}

/**
 * [JTextField] with placeholder text
 * @see onShowPlaceholder
 * @see onHidePlaceholder
 * @see KPlaceholderComponent
 */
@Suppress("ProtectedInFinal")
@FullyOpen
class KPlaceholderTextFieldImpl(
	doc: Document? = null,
	initialText: String? = null,
	columns: Int = 0,
	override var placeholderText: String? = null,
): JTextField(doc, initialText, columns), KPlaceholderComponent<KPlaceholderTextFieldImpl> {
	override var showingPlaceholder: Boolean = false
		protected set

	protected var onShowPlaceholder: (KPlaceholderTextFieldImpl.() -> Unit)? = null
	protected var onHidePlaceholder: (KPlaceholderTextFieldImpl.() -> Unit)? = null

	init {
		kFocusListener(object: FocusListener {
			override fun focusGained(event: FocusEvent) {
				if(showingPlaceholder) {
					hidePlaceholder()
				}
			}

			override fun focusLost(event: FocusEvent) {
				if(!showingPlaceholder && text.isNullOrBlank()) {
					showPlaceholder()
				}
			}
		})

		// since onShowPlaceholder/onHidePlaceholder
		// may be called in the setup block, we should wait
		// until we've actually been added to something
		kAncestorAddedListener {
			if(text.isNullOrBlank()) {
				showPlaceholder()
			}
		}
	}

	override fun getText() = if(showingPlaceholder) "" else super.getText()

	override fun onShowPlaceholder(handler: (KPlaceholderTextFieldImpl.() -> Unit)) {
		val current = onShowPlaceholder
		if(current == null) {
			onShowPlaceholder = handler
		} else {
			onShowPlaceholder = {
				current.invoke(this)
				handler.invoke(this)
			}
		}
	}

	override fun onHidePlaceholder(handler: (KPlaceholderTextFieldImpl.() -> Unit)) {
		val current = onHidePlaceholder
		if(current == null) {
			onHidePlaceholder = handler
		} else {
			onHidePlaceholder = {
				current.invoke(this)
				handler.invoke(this)
			}
		}
	}

	override fun updatePlaceholder() {
		if(showingPlaceholder) {
			hidePlaceholder()
		}

		if(!isFocusOwner && text.isNullOrBlank()) {
			showPlaceholder()
		}
	}

	protected fun showPlaceholder() {
		this.text = placeholderText
		this.showingPlaceholder = true

		onShowPlaceholder?.invoke(this)
	}

	protected fun hidePlaceholder() {
		this.text = ""
		this.showingPlaceholder = false

		onHidePlaceholder?.invoke(this)
	}
}