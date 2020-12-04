package tictactoeai;

public class HardAI implements Player{

    @Override
    public void move(Field field) {
        System.out.println(field);
        System.out.println("Making move level \"hard\"");
        makeBestMove(field);
    }
    
    private void makeBestMove(Field field) {
        int bestScore = Integer.MIN_VALUE;
        int bestX = -1;
        int bestY = -1;
        
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (field.isFree(i, j)) {
                    field.set(i, j);
                    int score = minimax(field, true);
                    field.unset(i, j);
                    
                    if (score > bestScore) {
                        bestScore = score;
                        bestX = i;
                        bestY = j;
                    }
                }
            }
        }
        
        field.set(bestX, bestY);
    }
    
    private int minimax(Field field, boolean isOwn) {
        if (!field.continues()) {
            if (isOwn) {
                return 10;
            } else {
                return -10;
            }
        } else if (field.isFilled()) {
            return 0;
        }
        
        int bestScore;
        
        if (isOwn) {
            bestScore = Integer.MAX_VALUE;
            
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (field.isFree(i, j)) {
                        field.set(i, j);
                        int score = minimax(field, false);
                        field.unset(i, j);
                        
                        if (score < bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
        } else {
            bestScore = Integer.MIN_VALUE;
            
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (field.isFree(i, j)) {
                        field.set(i, j);
                        int score = minimax(field, true);
                        field.unset(i, j);
                        
                        if (score > bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
        }
        
        return bestScore;
    }
    
}
