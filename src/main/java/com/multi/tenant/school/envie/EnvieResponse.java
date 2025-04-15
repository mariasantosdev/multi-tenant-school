package com.multi.tenant.school.envie;

public record EnvieResponse(
        String name
) {
    public static EnvieResponse convert(Envie envie) {
        return new EnvieResponse(envie.getName());
    }
}
