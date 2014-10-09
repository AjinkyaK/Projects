__author__ = 'Ajinkya'
import random
import queue
import timeit

start = timeit.default_timer()

#This class contains the structure of the Chess board
class Board:

    Max_horse1=None # Horse1_player1
    Max_horse2=None #Horse2_player1
    Min_horse1=None #Horse1_player2
    Min_horse2=None #Horse2 player2
    heuristic_value=0   #stores the heuristics
    All_possible_states = []    #stores the children

    def __init__(self, Max_horse1_name, Max_horse1_x, Max_horse1_y, Max_horse2_name, Max_horse2_x, Max_horse2_y, Min_Horse1_name, Min_horse1_x,
             Min_horse1_y, Min_horse2_name, Min_horse2_x, Min_horse2_y):
        self.Max_horse1=(Max_horse1_name,Max_horse1_x,Max_horse1_y)
        self.Max_horse2=(Max_horse2_name,Max_horse2_x,Max_horse2_y)
        self.Min_horse1=(Min_Horse1_name,Min_horse1_x,Min_horse1_y)
        self.Min_horse2=(Min_horse2_name,Min_horse2_x,Min_horse2_y)
        self.All_possible_states=[]
        self.heuristic_value=0


#This method determines the terminal state is reached or not
#@list of successor
def Terminal_state(list_of_successors):
    if  len(list_of_successors )== 0:
        return True
    else:
        return False


#This method returns the utiltiy value depending on Win or loss
#type - determines which has won or lost
def Utility_func(type):
    if type == 'Min':
        return -1
    elif type == 'Max':
        return 1

#sotres the list of valid children along the path
States_of_Min_Max=[]


def best_move(state):

    children = successor(state,'Max')
    print(str(len(children)))
    Max_of_state={}
    count=0
    Max_temp=None
    Max_h =0
    alpha = - float("inf")
    beta  =  float("inf")
    States_of_Min_Max.append(state)
    h_v = Max_value(state,alpha,beta)
    state.heuristic_value=h_v
    States_of_Min_Max.clear()
    #print( )
    i=h_v[0]
    print("(",i.Max_horse1[0],i.Max_horse1[1],",", i.Max_horse1[2],")" , "(",i.Max_horse2[0], i.Max_horse2[1],"," ,i.Max_horse2[2],")", "(",i.Min_horse1[0],
           i.Min_horse1[1] ,",", i.Min_horse1[2], ",)" ,"(",i.Min_horse2[0],i.Min_horse2[1],",", i.Min_horse2[2],")" )
    return i

def Max_value(state,alpha, beta):
    global max_count
    #print("incrementing")
    max_count=+1
    temp1 =successor(state,'Max')

    #print(str(len(temp1)))
    if Terminal_state(temp1):
        h_value = Utility_func('Max')
        state.heuristic_value = h_value
        return [state,h_value]

    v=   float("-inf")
    i=int()
    for i in temp1:
        States_of_Min_Max.append(i)
        v=Max(v,Min_value(i,alpha,beta)[1])
        if v >= beta:
            return [i,v]
        alpha=Max(alpha,v)
    return [i,v]


def Min_value(state,alpha, beta):
    global min_count
    min_count=+1
    temp2=successor(state,'Min')
    if Terminal_state(temp2):
        h_value = Utility_func('Min')
        state.heuristic_value=h_value
        return [state,h_value]

    v=float('inf')
    for i in temp2:
        States_of_Min_Max.append(i)
        v = Min(v, Max_value(i,alpha,beta)[1])
        if v <= alpha:
            return [i,v]
        beta = Min(beta,v)
    return [i,v]

def Max(v, value_from_Min):
    if v > value_from_Min:
        return v
    else:
        return value_from_Min

def Min(v, value_from_Max):
    if v < value_from_Max:
        return v
    else:
        return value_from_Max


#This method checks if the move is valid or not
#x,y,state this are the arguments to be considered on the board of state

def valid_moves(x,y,state):
    if len(States_of_Min_Max) !=0:
        for i in States_of_Min_Max:
            if i.Max_horse1[1] == x and i.Max_horse1[2] == y:
                return False
            elif i.Max_horse2[1] == x and i.Max_horse2[2] == y:
                return False
            elif i.Min_horse1[1]== x and i.Min_horse1[2] == y:
                return False
            elif i.Min_horse2[1] == x and i.Min_horse2[2] == y:
                return False
        else:
            return True
    else:
        return True



#This method generates the list of successor and creates a new object
#returns the list of successors
def successor(state, type):

    if type == 'Max':
        if len(state.Max_horse1)!=0:
            horse1_x = state.Max_horse1[1]
            horse1_y = state.Max_horse1[2]
            boundary_of_horse1_x = horse1_x-2
            if boundary_of_horse1_x >=0:
                possible_move_of_horse1_x1_sideways= horse1_x-2   #move 2x and 1y
                possible_move_of_horse1_y1_sideways= horse1_y-1    # sleeping L down
                possible_move_of_horse1_y2_sideways= horse1_y+1    # sleeping L up
                if valid_moves(possible_move_of_horse1_x1_sideways,possible_move_of_horse1_y1_sideways,state):
                    temp = Board(state.Max_horse1[0], possible_move_of_horse1_x1_sideways,possible_move_of_horse1_y1_sideways, state.Max_horse2[0], state.Max_horse2[1], state.Max_horse2[2],
                             state.Min_horse1[0], state.Min_horse1[1],state.Min_horse1[2], state.Min_horse2[0],state.Min_horse2[1],state.Min_horse2[2])
                    #States_of_Min_Max.append(temp)
                    state.All_possible_states.append(temp)
                if valid_moves(possible_move_of_horse1_x1_sideways,possible_move_of_horse1_y2_sideways,state):
                    temp = Board(state.Max_horse1[0], possible_move_of_horse1_x1_sideways,possible_move_of_horse1_y2_sideways, state.Max_horse2[0], state.Max_horse2[1], state.Max_horse2[2],
                        state.Min_horse1[0], state.Min_horse1[1],state.Min_horse1[2], state.Min_horse2[0],state.Min_horse2[1],state.Min_horse2[2])
                    state.All_possible_states.append(temp)
                    #States_of_Min_Max.append(temp)

            boundary_of_horse1_y=horse1_y-2
            if boundary_of_horse1_y >=0:
                possible_move_of_horse1_x1_upwards=  horse1_x-1
                possible_move_of_horse1_y1_upwards= horse1_y-2
                possible_move_of_horse1_x2_upwards= horse1_x+1
                #possible_move_of_horse1_y2_upwards=horse1_y+2
                if valid_moves(possible_move_of_horse1_x1_upwards, possible_move_of_horse1_y1_upwards,state):
                    temp = Board( state.Max_horse1[0],possible_move_of_horse1_x1_upwards, possible_move_of_horse1_y1_upwards,state.Max_horse2[0], state.Max_horse2[1], state.Max_horse2[2],
                             state.Min_horse1[0], state.Min_horse1[1],state.Min_horse1[2], state.Min_horse2[0],state.Min_horse2[1],state.Min_horse2[2])
                    state.All_possible_states.append(temp)
                    #States_of_Min_Max.append(temp)
                if valid_moves(possible_move_of_horse1_x2_upwards, possible_move_of_horse1_y1_upwards,state):
                    temp = Board(state.Max_horse1[0],possible_move_of_horse1_x2_upwards, possible_move_of_horse1_y1_upwards, state.Max_horse2[0], state.Max_horse2[1], state.Max_horse2[2],
                             state.Min_horse1[0], state.Min_horse1[1],state.Min_horse1[2], state.Min_horse2[0],state.Min_horse2[1],state.Min_horse2[2])
                    state.All_possible_states.append(temp)
                    #States_of_Min_Max.append(temp)


        if len(state.Max_horse2)!=0:
            horse2_x=state.Max_horse2[1]
            horse2_y=state.Max_horse2[2]
            boundary_of_horse2_x= horse2_x-2
            if boundary_of_horse2_x >= 0:
                possible_move_of_horse2_x1_sideways=horse2_x-2
                possible_move_of_horse2_y1_sideways=horse2_y-1
                possible_move_of_horse2_y2_sideways=horse2_y+1
                if valid_moves(possible_move_of_horse2_x1_sideways, possible_move_of_horse2_y1_sideways,state):
                    temp = Board(state.Max_horse1[0],state.Max_horse1[1],state.Max_horse1[2], state.Max_horse2[0],possible_move_of_horse2_x1_sideways, possible_move_of_horse2_y1_sideways,
                             state.Min_horse1[0], state.Min_horse1[1],state.Min_horse1[2], state.Min_horse2[0],state.Min_horse2[1],state.Min_horse2[2])
                    state.All_possible_states.append(temp)
                    #States_of_Min_Max.append(temp)
                if valid_moves(possible_move_of_horse2_x1_sideways, possible_move_of_horse2_y2_sideways,state):
                    temp = Board(state.Max_horse1[0],state.Max_horse1[1],state.Max_horse1[2], state.Max_horse2[0],possible_move_of_horse2_x1_sideways, possible_move_of_horse2_y2_sideways,
                             state.Min_horse1[0], state.Min_horse1[1],state.Min_horse1[2], state.Min_horse2[0],state.Min_horse2[1],state.Min_horse2[2])
                    state.All_possible_states.append(temp)
                    #States_of_Min_Max.append(temp)

            boundary_of_horse2_y= horse2_y-2
            if boundary_of_horse2_y >=0:
                possible_move_of_horse2_x1_upwards=horse2_x-1
                possible_move_of_horse2_y1_upwards=horse2_y-2
                possible_move_of_horse2_x2_upwards=horse2_x+1
            #possible_move_of_horse2_y2_upwards=horse2_y+2.
                if valid_moves(possible_move_of_horse2_x1_upwards,possible_move_of_horse2_y1_upwards,state):
                    temp = Board(state.Max_horse1[0],state.Max_horse1[1],state.Max_horse1[2], state.Max_horse2[0],possible_move_of_horse2_x1_upwards, possible_move_of_horse2_y1_upwards,
                             state.Min_horse1[0], state.Min_horse1[1],state.Min_horse1[2], state.Min_horse2[0],state.Min_horse2[1],state.Min_horse2[2])
                    state.All_possible_states.append(temp)
                    #States_of_Min_Max.append(temp)
                if valid_moves(possible_move_of_horse2_x2_upwards, possible_move_of_horse2_y1_upwards,state):
                    temp = Board(state.Max_horse1[0],state.Max_horse1[1],state.Max_horse1[2], state.Max_horse2[0],possible_move_of_horse2_x2_upwards, possible_move_of_horse2_y1_upwards,
                             state.Min_horse1[0], state.Min_horse1[1],state.Min_horse1[2], state.Min_horse2[0],state.Min_horse2[1],state.Min_horse2[2])
                    state.All_possible_states.append(temp)
                    #States_of_Min_Max.append(temp)

    elif type == 'Min':
        if len(state.Min_horse1)!=0:
            horse3_x=state.Min_horse1[1]
            horse3_y=state.Min_horse1[2]
            boundary_of_horse3_x= horse3_x-2
            if boundary_of_horse3_x >=0:
                possible_move_of_horse3_x1_sideways=horse3_x-2
                possible_move_of_horse3_y1_sideways=horse3_y-1
                possible_move_of_horse3_y2_sideways=horse3_y+1
                if valid_moves(possible_move_of_horse3_x1_sideways,possible_move_of_horse3_y1_sideways,state):
                    temp = Board(state.Max_horse1[0],state.Max_horse1[1],state.Max_horse1[2], state.Max_horse2[0],state.Max_horse2[1], state.Max_horse2[2],
                             state.Min_horse1[0], possible_move_of_horse3_x1_sideways, possible_move_of_horse3_y1_sideways, state.Min_horse2[0],state.Min_horse2[1],state.Min_horse2[2])
                    state.All_possible_states.append(temp)
                    #States_of_Min_Max.append(temp)
                if valid_moves(possible_move_of_horse3_x1_sideways, possible_move_of_horse3_y2_sideways,state):
                    temp = Board(state.Max_horse1[0],state.Max_horse1[1],state.Max_horse1[2], state.Max_horse2[0],state.Max_horse2[1], state.Max_horse2[2],
                             state.Min_horse1[0], possible_move_of_horse3_x1_sideways, possible_move_of_horse3_y2_sideways, state.Min_horse2[0],state.Min_horse2[1],state.Min_horse2[2])
                    state.All_possible_states.append(temp)



            boundary_of_horse3_y= horse3_y-2
            if boundary_of_horse3_y >=0:
                possible_move_of_horse3_x1_upwards= horse3_x-1
                possible_move_of_horse3_y1_upwards= horse3_y-2
                possible_move_of_horse3_x2_upwards= horse3_x+1

                if valid_moves(possible_move_of_horse3_x1_upwards, possible_move_of_horse3_y1_upwards,state):
                    temp = Board(state.Max_horse1[0],state.Max_horse1[1],state.Max_horse1[2], state.Max_horse2[0],state.Max_horse2[1], state.Max_horse2[2],
                             state.Min_horse1[0], possible_move_of_horse3_x1_upwards, possible_move_of_horse3_y1_upwards, state.Min_horse2[0], state.Min_horse2[1], state.Min_horse2[2])
                    state.All_possible_states.append(temp)
                    #States_of_Min_Max.append(temp)
                if valid_moves(possible_move_of_horse3_x2_upwards, possible_move_of_horse3_y1_upwards,state):
                    temp = Board (state.Max_horse1[0],state.Max_horse1[1],state.Max_horse1[2], state.Max_horse2[0],state.Max_horse2[1], state.Max_horse2[2],
                             state.Min_horse1[0], possible_move_of_horse3_x2_upwards, possible_move_of_horse3_y1_upwards, state.Min_horse2[0], state.Min_horse2[1], state.Min_horse2[2])
                    state.All_possible_states.append(temp)
                    #States_of_Min_Max.append(temp)


        if len(state.Min_horse2)!=0:
            horse4_x=state.Min_horse2[1]
            horse4_y=state.Min_horse2[2]
            boundary_of_horse4_x= horse4_x-2
            if boundary_of_horse4_x >=0:
                possible_move_of_horse4_x1_sideways=horse4_x-2
                possible_move_of_horse4_y1_sideways=horse4_y-1
                possible_move_of_horse4_y2_sideways=horse4_y+1
                if valid_moves(possible_move_of_horse4_x1_sideways, possible_move_of_horse4_y1_sideways,state):
                    temp = Board(state.Max_horse1[0],state.Max_horse1[1],state.Max_horse1[2], state.Max_horse2[0],state.Max_horse2[1], state.Max_horse2[2],
                             state.Min_horse1[0], state.Min_horse1[1], state.Min_horse1[2], state.Min_horse2[0], possible_move_of_horse4_x1_sideways, possible_move_of_horse4_y1_sideways)
                    state.All_possible_states.append(temp)
                    #States_of_Min_Max.append(temp)
                if valid_moves(possible_move_of_horse4_x1_sideways, possible_move_of_horse4_y2_sideways,state):
                    temp = Board (state.Max_horse1[0],state.Max_horse1[1],state.Max_horse1[2], state.Max_horse2[0],state.Max_horse2[1], state.Max_horse2[2],
                             state.Min_horse1[0], state.Min_horse1[1], state.Min_horse1[2], state.Min_horse2[0], possible_move_of_horse4_x1_sideways, possible_move_of_horse4_y2_sideways)
                    state.All_possible_states.append(temp)
                    #States_of_Min_Max.append(temp)

            boundary_of_horse4_y= horse4_y-2
            if boundary_of_horse4_y >=0:
                possible_move_of_horse4_x1_upwards= horse4_x-1
                possible_move_of_horse4_y1_upwards= horse4_y-2
                possible_move_of_horse4_x2_upwards= horse4_x+1
                #possible_move_of_horse4_y2_upwards= horse4_y+2
                if valid_moves(possible_move_of_horse4_x1_upwards, possible_move_of_horse4_y1_upwards,state):
                    temp = Board(state.Max_horse1[0],state.Max_horse1[1],state.Max_horse1[2], state.Max_horse2[0],state.Max_horse2[1], state.Max_horse2[2],
                             state.Min_horse1[0],state.Min_horse1[1],state.Min_horse1[2], state.Min_horse1[0], possible_move_of_horse4_x1_upwards, possible_move_of_horse4_y1_upwards)
                    state.All_possible_states.append(temp)
                    #States_of_Min_Max.append(temp)
                if valid_moves(possible_move_of_horse4_x2_upwards, possible_move_of_horse4_y1_upwards,state) :
                    temp = Board (state.Max_horse1[0],state.Max_horse1[1],state.Max_horse1[2], state.Max_horse2[0],state.Max_horse2[1], state.Max_horse2[2],
                             state.Min_horse1[0], state.Min_horse1[1],state.Min_horse1[2], state.Min_horse2[0], possible_move_of_horse4_x2_upwards, possible_move_of_horse4_y1_upwards)
                    state.All_possible_states.append(temp)
                    #States_of_Min_Max.append(temp)

    return state.All_possible_states



def play(state):
    best_move(state)


First = Board('white1', 200,100, 'White2', 200,300, 'black1',300,400, 'black2',400,200)

#best_move(First)
play(First)
stop= timeit.default_timer()
print(stop-start)


