/*
 * Copyright (C) 2008 The Android Open Source Project
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

package info.mzimmermann.custominflater;

import java.util.ArrayList;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

public class CustomInflater extends LayoutInflater {

	private ArrayList<String> mClassPrefixList = new ArrayList<String>();

	public CustomInflater(LayoutInflater original, Context newContext) {
		this(original, newContext, true);
	}

	public CustomInflater(LayoutInflater original, Context newContext,
			boolean addPackageAsPrefix) {
		super(original, newContext);
		mClassPrefixList.add("android.widget.");
		if (addPackageAsPrefix)
			mClassPrefixList.add(newContext.getPackageName() + ".");
	}

	public void addPrefix(String prefix) {
		mClassPrefixList.add(prefix);
	}

	public void removePrefix(String prefix) {
		mClassPrefixList.remove(prefix);
	}

	@Override
	public LayoutInflater cloneInContext(Context newContext) {
		return new CustomInflater(this, newContext);
	}

	@Override
	protected View onCreateView(String name, AttributeSet attrs)
			throws ClassNotFoundException {
		View view = null;

		for (int i = 0; i < mClassPrefixList.size(); i++) {
			try {
				view = createView(name, mClassPrefixList.get(i), attrs);
				if (view != null)
					break;
			} catch (ClassNotFoundException e) {
				if (i == mClassPrefixList.size() - 1)
					throw e;
			}
		}

		return view;
	}
}