package de.wbd.cd.cakedashboard.dto;

public enum OperationId {
    LMR_USDT_DFI ("Liquidity mining reward USDT-DFI"),
    ADD_L_ETH_DFI ("Add liquidity ETH-DFI");

    private String name;

    OperationId(String s) {
        this.name = s;
    }
}
