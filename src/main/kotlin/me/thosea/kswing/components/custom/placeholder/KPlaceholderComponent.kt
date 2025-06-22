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

package me.thosea.kswing.components.custom.placeholder

import javax.swing.text.JTextComponent

interface KPlaceholderComponent<T: JTextComponent> {
	/**
	 * Whether the placeholder text is currently be shown.
	 *
	 * Placeholder text is shown when the component is not focussed
	 * and the text is blank or null.
	 */
	val showingPlaceholder: Boolean

	var placeholderText: String?

	/**
	 * Adds a listener for when the placeholder is shown.
	 * The placeholder is shown when the component loses focus with
	 * no inputted text.
	 * @see showingPlaceholder
	 */
	fun onShowPlaceholder(handler: (T.() -> Unit))

	/**
	 * Adds a listener for when the placeholder is hidden.
	 * The placeholder is hidden when the component was showing its placeholder
	 * and then gains focus.
	 * @see showingPlaceholder
	 */
	fun onHidePlaceholder(handler: (T.() -> Unit))

	/**
	 * If a placeholder is being shown, it will be hidden and reshown.
	 * If the placeholder is not being shown (i.e. component is focussed or there
	 * is inputted text), this method will do nothing.
	 *
	 * Use this if you change [onShowPlaceholder] or [placeholderText] after
	 * the component is added to a container.
	 */
	fun updatePlaceholder()
}