package com.paulo.motionanimation.ui.util

import android.content.Context

fun getJson(context: Context,idJson: Int): String {
  return   context.resources.openRawResource(idJson)
      .readBytes()
      .decodeToString()
}