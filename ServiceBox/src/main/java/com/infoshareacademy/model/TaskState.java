package com.infoshareacademy.model;

public enum TaskState {
    CREATED {
        @Override
        public String toString(){
            return "Nowe zlecenie";
        }
    },
    WAITING_FOR_PARTS{
        @Override
        public String toString(){
            return "Oczekiwanie na części";
        }
    },
    IN_PROGRESS{
        @Override
        public String toString(){
            return "W trakcie realizacji";
        }
    },
    FINISHED{
        @Override
        public String toString(){
            return "Zlecenie zamknięte";
        }
    }
}
