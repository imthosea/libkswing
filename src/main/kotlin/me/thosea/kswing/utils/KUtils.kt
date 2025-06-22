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

@file:Suppress("Unused")

package me.thosea.kswing.utils

import me.thosea.kswing.states.KStateBase
import java.awt.GridBagConstraints
import java.awt.Image
import java.awt.Insets
import java.awt.image.BufferedImage
import javax.swing.Icon
import javax.swing.ImageIcon

/**
 * Adds the [handler] to all the [states] listeners lists, then calls it
 */
fun kUseStates(vararg states: KStateBase<*>, handler: () -> Unit) {
	for(state in states) {
		state.listenNoarg(handler)
	}
	handler.invoke()
}

/**
 * Adds the [handler] to all the [states] listeners lists
 */
fun kListenStates(vararg states: KStateBase<*>, handler: () -> Unit) {
	for(state in states) {
		state.listenNoarg(handler)
	}
}

/**
 * Makes [GridBagConstraints]
 *
 * [Docs](https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html)
 */
fun kBagConstraints(
	gridx: Int = GridBagConstraints.RELATIVE,
	gridy: Int = GridBagConstraints.RELATIVE,
	gridwidth: Int = 1,
	gridheight: Int = 1,
	weightx: Double = 0.0,
	weighty: Double = 0.0,
	anchor: Int = GridBagConstraints.CENTER,
	fill: Int = GridBagConstraints.NONE,
	insets: Insets = Insets(0, 0, 0, 0),
	ipadx: Int = 0,
	ipady: Int = 0,
): GridBagConstraints {
	return GridBagConstraints().apply {
		this.gridx = gridx
		this.gridy = gridy
		this.gridwidth = gridwidth
		this.gridheight = gridheight

		this.weightx = weightx
		this.weighty = weighty
		this.anchor = anchor
		this.fill = fill

		this.insets = insets
		this.ipadx = ipadx
		this.ipady = ipady
	}
}

/**
 * Gets an [Image] from the [Icon].
 *
 * If the [icon] is an [ImageIcon], it will return [ImageIcon.image],
 * otherwise the result of [kBufferImageFromIcon] will be returned.
 */
fun kImageFromIcon(icon: Icon): Image {
	return if(icon is ImageIcon) {
		icon.image
	} else {
		kBufferImageFromIcon(icon)
	}
}

/**
 * Paints the [icon] into a [BufferedImage]
 */
fun kBufferImageFromIcon(icon: Icon, imageType: Int = BufferedImage.TYPE_INT_ARGB): BufferedImage {
	val image = BufferedImage(icon.iconWidth, icon.iconHeight, imageType)
	val graphics = image.createGraphics()
	try {
		icon.paintIcon(null, graphics, 0, 0)
	} finally {
		graphics.dispose()
	}
	return image
}