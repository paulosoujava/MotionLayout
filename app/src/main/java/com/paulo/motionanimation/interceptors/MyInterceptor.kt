package com.paulo.motionanimation.interceptors

import android.content.Context
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okio.Buffer
import okio.BufferedSink
import okio.GzipSink
import okio.Okio
import okio.buffer
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit


class MyInterceptor : Interceptor {

    //Aqui, no intercept(), podemos realizar qualquer ação que quisermos dentro dele.
    override fun intercept(chain: Interceptor.Chain): Response {
        /**
         * Our API Call will be intercepted here
         */
        return TODO("Provide the return value")
    }
}

//E para usar o interceptador, podemos usar conforme abaixo:
fun myHttpClient(): OkHttpClient {
    val builder = OkHttpClient().newBuilder()
        .addInterceptor(MyInterceptor())
    return builder.build()
}


//Registrando os erros centralmente
class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response = chain.proceed(request)
        when (response.code) {
            400 -> {
                //Show Bad Request Error Message
            }

            401 -> {
                //Show UnauthorizedError Message
            }

            403 -> {
                //Show Forbidden Message
            }

            404 -> {
                //Show NotFound Message
            }
            // ... and so on
        }
        return response
    }
}
//.addInterceptor(ErrorInterceptor())


class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxAge(10, TimeUnit.DAYS)
            .build()
        return response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }
}

fun cache(context: Context) =
    OkHttpClient().newBuilder()
        .cache(Cache(File(context.cacheDir, "http-cache"), 10L * 1024L * 1024L)) // 10 MiB
        .addNetworkInterceptor(CacheInterceptor())
        .build();

//Aqui, se vemos, não estamos usando o, addInterceptor()mas usando
//addNetworkInterceptor()para o caso de uso. Isso ocorre porque, nesse
// caso, a operação está acontecendo na camada de rede.

/*
Mas há algo importante que precisamos considerar ao criar o primeiro aplicativo off-line.

A resposta em cache será retornada somente quando a Internet estiver disponível, pois o OkHttp foi projetado dessa forma.
Quando a Internet está disponível e os dados são armazenados em cache, ele retorna os dados do cache.
Mesmo quando os dados são armazenados em cache e a Internet não está disponível, ele retorna o erro "sem internet disponível".

O que fazer agora?
Podemos usar o seguinte ForceCacheInterceptorna camada de Aplicativo,
além do anterior (CacheInterceptor, somente se não estiver habilitado no servidor) .
 Para implementar no código, vamos criar um ForceCacheInterceptorconforme abaixo:
 */
class ForceCacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        if (!IsInternetAvailable()) {
            builder.cacheControl(CacheControl.FORCE_CACHE);
        }
        return chain.proceed(builder.build());
    }
}

fun IsInternetAvailable() = false

//para add:
fun context(context: Context)= OkHttpClient().newBuilder()
    .cache(Cache(File(context.cacheDir, "http-cache"), 10L * 1024L * 1024L)) // 10 MiB
    .addNetworkInterceptor(CacheInterceptor()) // only if Cache-Control header is not enabled from the server
    .addInterceptor(ForceCacheInterceptor())
    .build();
//Aqui, estamos adicionando ForceCacheInterceptorOkHttpClient usando addInterceptor()
// e não addNetworkInterceptor()como queremos que funcione na camada de aplicativo.


//Adicionando o cabeçalho como token de acesso centralmente
//Digamos que temos que fazer as chamadas de API e adicionar o Cabeçalho de Autorização em todas as chamadas de API.
// Podemos usá-lo individualmente ou podemos centralizá-lo usando o Interceptor.
class AuthTokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .header("Authorization", "AuthToken")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
// Primeiro, obtemos o token para o cabeçalho de nosso armazenamento local como um SharedPreference.
// Aqui, interceptamos a solicitação original que acionamos no aplicativo usando chain.request()
// e a configuramos como originalRequest.
// Em seguida, construímos a solicitação novamente adicionando o cabeçalho com a chave e o valor
// necessários para fazer a chamada de rede.
// Em seguida, construiremos a solicitação novamente e retornaremos a resposta usando
// chain.proceed(request), passando a nova solicitação que está com o cabeçalho Authorization.
// Assim podemos centralizar o Header que é comum em todas as API Calls. Agora para
// utilizá-lo no OkHttpClient, faremos conforme abaixo:
//
// .addInterceptor(AuthTokenInterceptor())
//
// Atualizando o token de acesso em um único local
// Digamos que temos um caso de uso quando recebemos um erro 401 no Error Interceptor e
// precisamos atualizar o token de autenticação porque temos um erro não autorizado.
// Podemos fazer isso usando o seguinte:
//
// override fun intercept(chain: Interceptor.Chain): Response {
//   val accessToken = //our access Token
//   val request = chain.request().newBuilder()
//                 .addHeader("Authorization", accessToken)
//                 .build()
//  val response = chain.proceed(request)
//  if (response.code() == 401) {
//     val newToken: String = //fetch from some other source
//     if (newToken != null) {
//       val newRequest =  chain.request().newBuilder()
//        .addHeader("Authorization", newToken)
//        .build()
//        return chain.proceed(newRequest)
//      }
//   }
// return response
// }
//Se obtivermos o response.code()as 401 , ou seja, Unauthorized ,
// atualizaremos o token aqui, modificaremos a solicitação adicionando
// o novo cabeçalho e faremos a nova solicitação ao servidor.

//E isso deve ser sincronizado para evitar o problema de simultaneidade.

//Obs.: Outra forma mais flexível na hora de atualizar o Access Token é utilizar a interface Authenticator do OkHttp.








/*
Ativando o Gzip no Android End
Gzip é usado para compressão de dados. Também no Android, podemos usar o Gzip para a compactação usando o interceptador.

Então, ao obter uma resposta, OkHttp automaticamente respeita o
cabeçalho ( codificação de conteúdo ) e descompacta os dados e retorna,
mas digamos que quando temos que enviar dados compactados para um servidor,
temos que escrever nosso próprio interceptor.

Podemos criar a GzipRequestInterceptorclasse conforme abaixo (retirada do repositório OkHttp)
 */
class GzipRequestInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        if (originalRequest.body == null || originalRequest.header("Content-Encoding") != null) {
            return chain.proceed(originalRequest)
        }
        val compressedRequest = originalRequest.newBuilder()
            .header("Content-Encoding", "gzip")
            .method(originalRequest.method, forceContentLength(gzip(originalRequest.body)))
            .build()
        return chain.proceed(compressedRequest)
    }

    @Throws(IOException::class)
    private fun forceContentLength(requestBody: RequestBody): RequestBody {
        val buffer = Buffer()
        requestBody.writeTo(buffer)
        return object : RequestBody() {
            override fun contentType(): MediaType? {
                return requestBody.contentType()
            }

            override fun contentLength(): Long {
                return buffer.size
            }

            @Throws(IOException::class)
            override fun writeTo(sink: BufferedSink) {
                sink.write(buffer.snapshot())
            }
        }
    }

    private fun gzip(body: RequestBody?): RequestBody {
        return object : RequestBody() {
            override fun contentType(): MediaType? {
                return body!!.contentType()
            }

            override fun contentLength(): Long {
                return -1 // We don't know the compressed length in advance!
            }

            @Throws(IOException::class)
            override fun writeTo(sink: BufferedSink) {
                val gzipSink: BufferedSink = GzipSink(sink).buffer()
                body!!.writeTo(gzipSink)
                gzipSink.close()
            }
        }
    }
}
//Para usar o interceptador podemos usar como abaixo:
//
//.addInterceptor(GzipRequestInterceptor())


















