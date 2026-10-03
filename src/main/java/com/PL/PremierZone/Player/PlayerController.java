package com.PL.PremierZone.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(path = "api/v2/player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/getplayer")
    public List<Player> getPlayers(@RequestParam(required = false) String team,
                                   @RequestParam(required = false) String name,
                                   @RequestParam(required = false) String position,
                                   @RequestParam(required = false) String nation) {
        if (team != null && position != null){
            return playerService.getPlayersByPositionAndTeam(team,position);
        }
        else if (nation != null){
            return playerService.getPlayersByNation(nation);
        }
        else if (name != null){
            return playerService.getPlayersByName(name);
        }
        else if (position != null){
            return playerService.getPlayersByPosition(position);
        }
        else if (team != null){
            return playerService.getPlayersByTeamName(team);
        }
        else{
        return playerService.getPlayers();}
    }

    @PostMapping()
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        Player createdPlayer = playerService.addPlayer(player);
        return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {
        Player updatedPlayer = playerService.updatePlayer(player);
        if (updatedPlayer != null){
            return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
        }
        return new ResponseEntity<>(updatedPlayer, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{playerName}")
    public ResponseEntity<String> deletePlayer(@PathVariable String playerName) {
        playerService.deletePlayer(playerName);
        return new ResponseEntity<>("Player deleted successfully", HttpStatus.OK);
    }

}
