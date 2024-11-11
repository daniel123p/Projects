$arrayGames = @(Get-Content C:\Users\Daniel\OneDrive\Desktop\Games\GameFilePath.txt)
$gameList = @(Get-Content C:\Users\Daniel\OneDrive\Desktop\Games\GameListTXT.txt)
$wrapList = ($gameList -join "`n")
$test = Read-Host "These Games Are Available For Play: `n` $wrapList  `n`Input the games corresponding number, then press Enter to play it."
Start Process -FilePath $arrayGames[$test]
Write-Host "Enjoy The Games!"
