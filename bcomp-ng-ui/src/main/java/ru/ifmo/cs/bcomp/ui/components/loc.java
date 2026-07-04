package ru.ifmo.cs.bcomp.ui.components;

import java.util.ListResourceBundle;

public class loc extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private static final Object[][] contents = {
            {"read", "F6 Чтение"},
            {"write", "F5 Запись"},
            {"setip", "F4 Ввод адреса"},
            {"start", "F7 Пуск"},
            {"continue", "F8 Продолжение"},
            {"tick", "Shift+F9 Такт"},
            {"run", "F9   Работа"},
            {"stop", "F9 Останов"},
            {"basename", "Базовая ЭВМ"},
            {"assembler", "Ассемблер"},
            {"output", "КВУ-1 (DR(W): 0x2, SR(R)/MR(W): 0x3)"},
            {"input", "КВУ-2 (DR(R): 0x4, SR(R)/MR(W): 0x5)"},
            {"IO", "КВУ-3 (DR(R/W): 0x6, SR(R)/MR(W): 0x7)"},
            {"IO4", "КВУ-4 (DRin: 0x8, DRout: 0x9, SR: 0xA, MR: 0xB)"},
            {"ready", "Готов"},
            {"DEV-1", "КВУ 1"},
            {"DEV-2", "КВУ 2"},
            {"DEV-3", "КВУ 3"},
            {"DEV-4", "КВУ 4"},
            {"DEV-5", "ВУ 5"},
            {"DEV-6", "ВУ 6"},
            {"DEV-7", "ВУ 7"},
            {"DEV-8", "ВУ 8"},
            {"DEV-9", "ВУ 9"},
            {"printer", "Текстовый принтер (DR(W): 0xC, SR(R): 0xD, MR(W): 0xE)"},
            {"ticker", "Бегущая строка (DR(W): 0x10, SR(R): 0x11, MR(W): 0x12)"},
            {"ssd", "Семисегментный индикатор (DR(W): 0x14, SR(R): 0x15, MR(W): 0x16)"},
            {"kbd", "Клавиатура (DR(R): 0x18, SR(R): 0x19, MR(W): 0x1A)"},
            {"numpad", "Цифровая клавиатура (DR(R): 0x1C, SR(R): 0x1D, MR(W): 0x1E)"},
            {"error", "Ошибка"},
            {"stopRunning", "Для компиляции остановите выполняющуюся программу"},
            {"compile", "Компилировать"}
    };
}