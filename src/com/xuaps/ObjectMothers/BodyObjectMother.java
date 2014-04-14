package com.xuaps.ObjectMothers;

import com.xuaps.data.*;
import com.xuaps.data.Exception;

import java.awt.peer.ChoicePeer;
import java.util.ArrayList;

/**
* Created by david.vilchez on 14/04/14.
*/
public class BodyObjectMother {

    public Body CreateBody() {
        Body body=new Body();

        Trace trace=new Trace();
        body.setTrace(trace);

        trace.setException(new Exception());
        trace.setFrames(new ArrayList<Frame>());
        return body;
    }


}
