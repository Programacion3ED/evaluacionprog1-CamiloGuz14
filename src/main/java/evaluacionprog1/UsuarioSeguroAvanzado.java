package evaluacionprog1;
public class UsuarioSeguroAvanzado {


//VERSION 8, PRUEBA

    private String username;
    private String password;
    private int intentosFallidos;
    private boolean bloqueado;
    private int maxIntentos;
    private boolean accesoExitoso;

    public UsuarioSeguroAvanzado(String username, String password, int maxIntentos) {
        this.username = username;
        this.password = password;
        this.intentosFallidos = 0;
        this.bloqueado = false;
        this.accesoExitoso = false;

        if (maxIntentos <= 0) {
            this.maxIntentos = 3;
        } else {
            this.maxIntentos = maxIntentos;
        }
    }

    public String getUsername() {
        return username;
    }

    public int getIntentosFallidos() {
        return intentosFallidos;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public int getMaxIntentos() {
        return maxIntentos;
    }

    public boolean isAccesoExitoso() {
        return accesoExitoso;
    }

    public boolean autenticar(String passwordIngresada) {
        if (bloqueado) {
            return false;
        }

        if (password.equals(passwordIngresada)) {
            intentosFallidos = 0;
            accesoExitoso = true;
            return true;
        } else {
            intentosFallidos++;

            if (intentosFallidos >= maxIntentos) {
                bloqueado = true;
            }

            return false;
        }
    }

    public void reiniciarAcceso() {
        intentosFallidos = 0;
        bloqueado = false;
    }

    public boolean cambiarPassword(String actual, String nueva) {
        if (bloqueado) {
            return false;
        }

        if (!password.equals(actual)) {
            return false;
        }

        if (!validarPassword(nueva)) {
            return false;
        }

        password = nueva;
        return true;
    }

    public boolean validarPassword(String nueva) {
        if (nueva == null || nueva.length() < 8) {
            return false;
        }

        boolean tieneMayuscula = false;
        boolean tieneMinuscula = false;
        boolean tieneNumero = false;

        for (int i = 0; i < nueva.length(); i++) {
            char caracter = nueva.charAt(i);

            if (Character.isUpperCase(caracter)) {
                tieneMayuscula = true;
            }

            if (Character.isLowerCase(caracter)) {
                tieneMinuscula = true;
            }

            if (Character.isDigit(caracter)) {
                tieneNumero = true;
            }
        }

        return tieneMayuscula && tieneMinuscula && tieneNumero;
    }
}