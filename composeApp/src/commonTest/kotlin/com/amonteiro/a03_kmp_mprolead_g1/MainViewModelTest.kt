package com.amonteiro.a03_kmp_mprolead_g1

import com.amonteiro.a03_kmp_mprolead_g1.BuildConfig
import com.amonteiro.a03_kmp_mprolead_g1.data.remote.PhotographerAPI
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertTrue

class MainViewModelTest {

    // Test 1 : toujours réussi — vérifie juste que le code commun fonctionne
    @Test
    fun greetingStartsWithHello() {
        val message = Greeting().greet()
        assertTrue(message.startsWith("Hello"), "Attendu 'Hello...', reçu : $message")
    }

    // Test 2 : appel réel à PhotographerAPI — vérifie que PHOTOGRAPHER_API_KEY est bien
    // récupérée depuis les secrets GitHub
    @Test
    fun photographerApiReturnsData() = runTest {
        assertTrue(
            BuildConfig.PHOTOGRAPHER_API_KEY.isNotEmpty(),
            "PHOTOGRAPHER_API_KEY est vide ! Vérifier le secret GitHub."
        )
        val client = HttpClient {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
            }
        }
        val api = PhotographerAPI(client)
        val photographers = api.loadPhotographers()
        assertTrue(
            photographers.isNotEmpty(),
            "L'API doit retourner au moins un photographe. Clé API invalide ou manquante."
        )
        client.close()
    }
}
