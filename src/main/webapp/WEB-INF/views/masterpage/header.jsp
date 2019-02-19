<script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>

<script>
	if(Cookies.get("auth")){
		Cookies.set("auth", Cookies.get('auth'));
	}
</script>

<fieldset style="display: inline-block; border-radius: 8px;">
	<a href="/club/list">Clubs</a> |
	<a href="/corredor/list">Corredores</a> |
	<a href="/patrocinador/list">Patrocinadores</a> |
	<a href="/resultado/list">Resultados</a> |
	<a href="/prueba/list">Pruebas</a>
</fieldset>

<br><br>