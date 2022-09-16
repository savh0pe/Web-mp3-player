<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>

    <h3 class="mb-3">Welcome to musical player web-app, ${name}!</h3>


    <button type="button" class="btn btn-secondary float-right" data-toggle="tooltip" data-placement="left"
            title="You can add new music to server if you choose &quot;Add music&quot; below.
            This music will automatically be added to your profile.
            If you want to see every musical track on the server currently, you can choose &quot;Show all music&quot;.
            You can add this music to your profile.">
        Help
    </button>

    <a class="btn btn-primary mb-2" data-toggle="collapse" href="#addMusic" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add music track
    </a>

    <div class="collapse" id="addMusic">
        <div class="form-group mt-3">
            <form action="/addMusic" method="post" class="form-inline" enctype="multipart/form-data">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="musicFile">
                        <label for="musicFile" class="custom-file-label">Choose file</label>
                    </div>
                </div>
                <div class="form-group ml-1">
                    <button class="btn btn-primary " type="submit">Добавить</button>
                </div>
            </form>
        </div>
    </div>



    <#if tracks??>
        <div class="card-columns" id="userMusic">
            <#list tracks as track>

                <div class="card">
                    <p class="text-center" style="color: black;">
                        ${track.name} - ${track.artist}
                    </p>
                    <audio style="width: 100%;"
                           src="/audio/${track.name + " (" + track.album + ") - " + track.artist + ".mp3"}"
                           controls></audio>

                </div>
            </#list>
        </div>
    </#if>
    <script>
        $('#musicFile').on('change', function () {
            var fileName = $(this).val().replace(/^.*[\\\/]/, '');
            $(this).next('.custom-file-label').html(fileName);
        })
    </script>

</@c.page>
