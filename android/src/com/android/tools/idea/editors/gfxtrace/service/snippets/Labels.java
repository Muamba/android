/*
 * Copyright (C) 2015 The Android Open Source Project
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
 *
 * THIS FILE WAS GENERATED BY codergen. EDIT WITH CARE.
 */
package com.android.tools.idea.editors.gfxtrace.service.snippets;

import org.jetbrains.annotations.NotNull;

import com.android.tools.rpclib.binary.*;
import com.android.tools.rpclib.schema.*;

import java.io.IOException;
import java.util.ArrayList;

public final class Labels extends KindredSnippets implements BinaryObject {
  //<<<Start:Java.ClassBody:1>>>
  private Pathway myPath;
  private String[] myLabels;

  // Constructs a default-initialized {@link Labels}.
  public Labels() {}


  public Pathway getPath() {
    return myPath;
  }

  public Labels setPath(Pathway v) {
    myPath = v;
    return this;
  }

  public String[] getLabels() {
    return myLabels;
  }

  public Labels setLabels(String[] v) {
    myLabels = v;
    return this;
  }

  @Override @NotNull
  public BinaryClass klass() { return Klass.INSTANCE; }


  private static final Entity ENTITY = new Entity("snippets", "Labels", "", "");

  static {
    ENTITY.setFields(new Field[]{
      new Field("path", new Interface("Pathway")),
      new Field("labels", new Slice("", new Primitive("string", Method.String))),
    });
    Namespace.register(Klass.INSTANCE);
  }
  public static void register() {}
  //<<<End:Java.ClassBody:1>>>

  /**
   * find the labels in the snippets.
   * @param snippets any kind of snippets.
   * @return the labels maybe null.
   */
  public static Labels fromSnippets(KindredSnippets[] snippets) {
    for (KindredSnippets obj : snippets) {
      if (obj instanceof Labels) {
        return (Labels)obj;
      }
    }
    return null;
  }

  public enum Klass implements BinaryClass {
    //<<<Start:Java.KlassBody:2>>>
    INSTANCE;

    @Override @NotNull
    public Entity entity() { return ENTITY; }

    @Override @NotNull
    public BinaryObject create() { return new Labels(); }

    @Override
    public void encode(@NotNull Encoder e, BinaryObject obj) throws IOException {
      Labels o = (Labels)obj;
      e.object(o.myPath.unwrap());
      e.uint32(o.myLabels.length);
      for (int i = 0; i < o.myLabels.length; i++) {
        e.string(o.myLabels[i]);
      }
    }

    @Override
    public void decode(@NotNull Decoder d, BinaryObject obj) throws IOException {
      Labels o = (Labels)obj;
      o.myPath = Pathway.wrap(d.object());
      o.myLabels = new String[d.uint32()];
      for (int i = 0; i <o.myLabels.length; i++) {
        o.myLabels[i] = d.string();
      }
    }
    //<<<End:Java.KlassBody:2>>>
  }
}