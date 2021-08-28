package com.maishare.themis.component.base.checker;

import com.maishare.themis.extension.Adaptive;
import com.maishare.themis.extension.ExtensionLoader;

@Adaptive
public class DefaultAssertMatcherExecutor implements AssertMatcherExecutor {

    private static final AssertMatcherChain matcherChain = ExtensionLoader.getExtensionLoader(AssertMatcherChain.class).getAdaptiveExtension();

    @Override
    public void match(MatcherContext matcherContext) {
        try {
            matcherChain.match(matcherContext);
        }finally {
        }
    }
}
