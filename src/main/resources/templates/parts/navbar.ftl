<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Player</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">

            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/">Your Music</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/allMusic">All Music</a>
                </li>
            </#if>
        </ul>

        <div class="navbar-text mr-2">${name?if_exists}</div>
        <#if user??>
            <@l.logout/>
        </#if>
    </div>
</nav>