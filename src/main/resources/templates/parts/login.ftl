<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> User Name: </label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control ${(usernameError??)?string('is-invalid','')}"
                       placeholder="Username" value="<#if user??>${user.username}</#if>"/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password: </label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid','')}"
                       placeholder="Password"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>

        <#if !isRegisterForm><a href="/registration">Register</a></#if>
        <input class="btn btn-primary" type="submit" value="${isRegisterForm?string("Sign Up","Sign In")}"/>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input class="btn btn-primary" type="submit" value="Sign Out"/>
    </form>
</#macro>