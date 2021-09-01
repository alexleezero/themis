package org.bravo.gaia.commons.money;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author lijian
 * @since 2021/8/30
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MultiCurrencyMoney {

    private long cent;

    private long currencyValue;

}
