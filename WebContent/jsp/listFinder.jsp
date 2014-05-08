<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="htmlElements/top.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-md-12">
			<h2>List Finder</h2>
			<p>
				Besoin d'aide pour choisir efficacement une structure de données ?<br />
				Suivez notre formulaire ci-dessous pour obtenir la structure la plus
				optimisée pour votre utilisation<br />
			</p>
			<div id="alertXmlRequest" class="alert alert-danger" style="display:none;">Oops !<br/>Il est impossible d'exécuter les requêtes sur le serveur. Votre navigateur n'est pas compatible avec XMLHttpRequest ou l'activeX Microsoft</div>
			<div id="alertFallenServer" class="alert alert-danger" style="display:none;">Oops !<br/>Il est impossible d'exécuter les requêtes sur le serveur. Le serveur est injoignable</div>
			<form role="form">
				<div class="question">
					<div class="panel panel-primary">
						<div class="panel-heading">
							${firstQuestion.questionValue}
						</div>
						<div class="panel-body">
							<select class="form-control" name="firstQuestion" id="firstQuestion" onchange="javascript:doAjaxRequestForNextQuestion('${firstQuestion.id}',this)">
								<option></option>
								<c:forEach items="${firstQuestion.listeReponse}" var="answer">
									<option value="${answer.key}">${answer.value }</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/listFinder.js"></script>
<script type="text/javascript">
	var racine = "${pageContext.request.contextPath}";
</script>
<%@include file="htmlElements/bottom.jsp"%>