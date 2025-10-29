import time
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))
driver.set_window_size(2500, 1180)

# --- Caminhos Absolutos Fixos
url_inicial = 'file:///C:/Users/super/Downloads/STREAM-FLOW/Stream%20Flow%20Project/index.html'

try:
    # Abrir a página inicial
    driver.get(url_inicial)
    print("✅ SUCESSO: Página 'index.html' aberta.")
    time.sleep(1)

    wait = WebDriverWait(driver, 10)
    barra_pesquisa = wait.until(EC.presence_of_element_located((By.CLASS_NAME, 'search-bar')))
    print("-> Barra de pesquisa encontrada com sucesso.")
    
    # Valida o atributo 'placeholder' do elemento.
    placeholder_text = barra_pesquisa.get_attribute('placeholder')
    assert placeholder_text == "Pesquisar"
    print(f"✅ TESTE 1 (get_attribute): Atributo 'placeholder' verificado com sucesso: '{placeholder_text}'")
    
    texto_pesquisa = "Batman"
    # Simula a digitação no campo de busca.
    barra_pesquisa.send_keys(texto_pesquisa)
    print(f"✅ TESTE 2 (send_keys): Texto '{texto_pesquisa}' digitado na barra de pesquisa.")
    time.sleep(5)
    
    # Limpa o conteúdo do campo de busca.
    barra_pesquisa.clear()
    print("✅ TESTE 3 (clear): Barra de pesquisa limpa com sucesso.")
    time.sleep(5)

    botao_info = driver.find_element(By.CSS_SELECTOR, 'a.btn.btn-secondary')
    botao_info.click()
    print("-> AÇÃO: Clicado no botão 'Mais Informações'.")
    
    wait.until(EC.url_contains('movie.html'))
    
    # Verifica se a URL atual corresponde à página esperada.
    url_atual = driver.current_url
    assert 'movie.html' in url_atual
    print(f"✅ TESTE 4 (driver.current_url): Redirecionamento para '{url_atual}' verificado.")

    # Verifica se o texto visível de um elemento está correto.
    descricao_filme = wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, 'p.movie-description')))
    texto_descricao = descricao_filme.text
    assert "lendário homem de aço" in texto_descricao
    print(f"✅ TESTE 5 (.text): Verificação de conteúdo de texto realizada com sucesso.")
    time.sleep(6)
    
    botao_assistir = driver.find_element(By.CSS_SELECTOR, 'a.btn-primary')
    botao_assistir.click()
    print("-> AÇÃO: Clicado no botão 'Assistir'.")
    
    wait.until(EC.url_contains('.mp4'))
    url_video = driver.current_url
    # Reutiliza o teste de redirecionamento para validar a navegação até o vídeo.
    assert '.mp4' in url_video
    print(f"✅ TESTE 4 (driver.current_url): Redirecionamento para o vídeo verificado com sucesso.")
    time.sleep(2)

    # Testa a funcionalidade de "voltar" do navegador.
    driver.back()
    print("✅ TESTE 6 (driver.back): Navegação no histórico ('voltar') funcionou corretamente.")
    time.sleep(5)


    print("\n🎉 TODOS OS TESTES FORAM CONCLUÍDOS COM SUCESSO! 🎉")

except Exception as e:
    print(f"\n❌ ERRO INESPERADO: Ocorreu um problema. Detalhes: {e}")

finally:
    print("\nFechando o navegador em 5 segundos...")
    time.sleep(5)
    driver.quit()