package com.pst.efi.biller.dto.notification;

public record PaymentNotificationResponse(MFEP MFEP) {
    public record MFEP(MsgHeader MsgHeader, MsgBody MsgBody) {
        public record MsgHeader(String TmStp, String GUID, TrsInf TrsInf, Result Result) {
            public record TrsInf(int SdrCode, String ResTyp) {}
            public record Result(int ErrorCode, String ErrorDesc, SeverityCodes Severity) {}
        }

        public record MsgBody(Transactions Transactions) {
            public record Transactions(TrxInf TrxInf) {
                public record TrxInf(String JOEBPPSTrx, String ProcessDate, String STMTDate, Result Result) {
                    public record Result(int ErrorCode, String ErrorDesc, SeverityCodes Severity) {}
                }
            }
        }
    }

    public enum SeverityCodes {
        INFO(0),
        WARNING(1),
        ERROR(2);

        private final int code;

        SeverityCodes(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }
}
