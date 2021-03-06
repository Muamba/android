/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.tools.idea.uibuilder.scene.target;

import com.android.tools.idea.uibuilder.scene.SceneComponent;
import com.android.tools.idea.uibuilder.scene.SceneContext;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * Guideline anchors
 */
public class GuidelineAnchorTarget extends AnchorTarget {
  boolean myIsHorizontal;

  @Override
  public int getMouseCursor() {
    if (myIsHorizontal) {
      return Cursor.N_RESIZE_CURSOR;
    }
    return Cursor.E_RESIZE_CURSOR;
  }

  @Override
  public int getPreferenceLevel() {
    return Target.GUIDELINE_ANCHOR_LEVEL;
  }

  public GuidelineAnchorTarget(@NotNull Type type, boolean isHorizontal) {
    super(type, false);
    myIsHorizontal = isHorizontal;
  }

  @Override
  public boolean layout(@NotNull SceneContext sceneTransform, int l, int t, int r, int b) {
    int dist = 4;
    SceneComponent parent = myComponent.getParent();
    if (myIsHorizontal) {
      myLeft = parent.getDrawX();
      myTop = t - dist;
      myRight = myLeft + parent.getDrawWidth();
      myBottom = t + dist;
    } else {
      myLeft = l - dist;
      myTop = parent.getDrawY();
      myRight = l + dist;
      myBottom = myTop + parent.getDrawHeight();
    }
    return false;
  }

}
