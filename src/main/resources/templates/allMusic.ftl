<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <#if tracks??>
        <div class="card-columns" id="userMusic">
            <#list tracks as track>

                <div class="card">
                    <#if user.tracks?seq_contains(track)>
                        <form action="/removeMusicFromProfile" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <input type="hidden" name="id" value="${track.id}">
                            <input type="submit" class="btn btnSubmitMusic" value="X">
                        </form>
                    <#else>
                        <form action="/addMusicToProfile" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <input type="hidden" name="id" value="${track.id}">
                            <input type="submit" class="btn btnSubmitMusic" value="+">
                        </form>
                    </#if>
                    <p class="text-center">
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
        $(".btnSubmitMusic").click(function (event) {
            event.preventDefault();
            var button=$(this);
            var action = $(this).parent().attr("action");
            var requestData = {id: $(this).prev().val()};
            var opAction = action == "/addMusicToProfile" ? "/removeMusicFromProfile" : "/addMusicToProfile";
            var p = $(this).val();
            var csrf = $(this).prev().prev().val();
            var opP = p == "+" ? "X" : "+";
            $.ajax({
                url: action,
                headers: {'X-CSRF-TOKEN': csrf},
                method: 'post',
                contentType: 'application/json',
                data: JSON.stringify(requestData),
                success: function (response) {
                    button.parent().attr('action', opAction);
                    button.val(opP);
                },
                error: function (response) {
                    alert("Произошла ошибка.\n");
                }
            });
        });
    </script>
</@c.page>