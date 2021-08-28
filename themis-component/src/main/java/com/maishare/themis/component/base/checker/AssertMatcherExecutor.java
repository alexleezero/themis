package com.maishare.themis.component.base.checker;

import com.maishare.themis.extension.SPI;

@SPI
public interface AssertMatcherExecutor {

    void match(MatcherContext matcherContext);
}
