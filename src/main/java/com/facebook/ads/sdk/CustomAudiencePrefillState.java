/**
 * Copyright (c) 2015-present, Facebook, Inc. All rights reserved.
 * <p>
 * You are hereby granted a non-exclusive, worldwide, royalty-free license to
 * use, copy, modify, and distribute this software in source code or binary
 * form for use in connection with the web services and APIs provided by
 * Facebook.
 * <p>
 * As with any software that integrates with the Facebook platform, your use
 * of this software is subject to the Facebook Developer Principles and
 * Policies [http://developers.facebook.com/policy/]. This copyright notice
 * shall be included in all copies or substantial portions of the software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package com.facebook.ads.sdk;

import com.facebook.ads.sdk.APIException.MalformedResponseException;
import com.google.gson.*;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * This class is auto-genereated.
 *
 * For any issues or feature requests related to this class, please let us know
 * on github and we'll fix in our codegen framework. We'll not be able to accept
 * pull request for this class.
 *
 */
public class CustomAudiencePrefillState extends APINode {
    @SerializedName("description")
    private String mDescription = null;
    @SerializedName("num_added")
    private Long mNumAdded = null;
    @SerializedName("status")
    private String mStatus = null;
    protected static Gson gson = null;

    public CustomAudiencePrefillState() {
    }

    public String getId() {
        return null;
    }

    public static CustomAudiencePrefillState loadJSON(String json, APIContext context) {
        CustomAudiencePrefillState customAudiencePrefillState = getGson().fromJson(json, CustomAudiencePrefillState.class);
        if (context.isDebug()) {
            JsonParser parser = new JsonParser();
            JsonElement o1 = parser.parse(json);
            JsonElement o2 = parser.parse(customAudiencePrefillState.toString());
            if (o1.getAsJsonObject().get("__fb_trace_id__") != null) {
                o2.getAsJsonObject().add("__fb_trace_id__", o1.getAsJsonObject().get("__fb_trace_id__"));
            }
            if (!o1.equals(o2)) {
                context.log("[Warning] When parsing response, object is not consistent with JSON:");
                context.log("[JSON]" + o1);
                context.log("[Object]" + o2);
            }
            ;
        }
        customAudiencePrefillState.context = context;
        customAudiencePrefillState.rawValue = json;
        return customAudiencePrefillState;
    }

    public static APINodeList<CustomAudiencePrefillState> parseResponse(String json, APIContext context, APIRequest request) throws MalformedResponseException {
        APINodeList<CustomAudiencePrefillState> customAudiencePrefillStates = new APINodeList<CustomAudiencePrefillState>(request, json);
        JsonArray arr;
        JsonObject obj;
        JsonParser parser = new JsonParser();
        Exception exception = null;
        try {
            JsonElement result = parser.parse(json);
            if (result.isJsonArray()) {
                // First, check if it's a pure JSON Array
                arr = result.getAsJsonArray();
                for (int i = 0; i < arr.size(); i++) {
                    customAudiencePrefillStates.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context));
                }
                ;
                return customAudiencePrefillStates;
            } else if (result.isJsonObject()) {
                obj = result.getAsJsonObject();
                if (obj.has("data")) {
                    if (obj.has("paging")) {
                        JsonObject paging = obj.get("paging").getAsJsonObject();


                        customAudiencePrefillStates.setPaging(paging);
                    }
                    if (obj.get("data").isJsonArray()) {
                        // Second, check if it's a JSON array with "data"
                        arr = obj.get("data").getAsJsonArray();
                        for (int i = 0; i < arr.size(); i++) {
                            customAudiencePrefillStates.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context));
                        }
                        ;
                    } else if (obj.get("data").isJsonObject()) {
                        // Third, check if it's a JSON object with "data"
                        obj = obj.get("data").getAsJsonObject();
                        boolean isRedownload = false;
                        for (String s : new String[]{"campaigns", "adsets", "ads"}) {
                            if (obj.has(s)) {
                                isRedownload = true;
                                obj = obj.getAsJsonObject(s);
                                for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                                    customAudiencePrefillStates.add(loadJSON(entry.getValue().toString(), context));
                                }
                                break;
                            }
                        }
                        if (!isRedownload) {
                            customAudiencePrefillStates.add(loadJSON(obj.toString(), context));
                        }
                    }
                    return customAudiencePrefillStates;
                } else if (obj.has("images")) {
                    // Fourth, check if it's a map of image objects
                    obj = obj.get("images").getAsJsonObject();
                    for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                        customAudiencePrefillStates.add(loadJSON(entry.getValue().toString(), context));
                    }
                    return customAudiencePrefillStates;
                } else {
                    // Fifth, check if it's an array of objects indexed by id
                    boolean isIdIndexedArray = true;
                    for (Map.Entry entry : obj.entrySet()) {
                        String key = (String) entry.getKey();
                        if (key.equals("__fb_trace_id__")) {
                            continue;
                        }
                        JsonElement value = (JsonElement) entry.getValue();
                        if (
                                value != null &&
                                        value.isJsonObject() &&
                                        value.getAsJsonObject().has("id") &&
                                        value.getAsJsonObject().get("id") != null &&
                                        value.getAsJsonObject().get("id").getAsString().equals(key)
                                ) {
                            customAudiencePrefillStates.add(loadJSON(value.toString(), context));
                        } else {
                            isIdIndexedArray = false;
                            break;
                        }
                    }
                    if (isIdIndexedArray) {
                        return customAudiencePrefillStates;
                    }

                    // Sixth, check if it's pure JsonObject
                    customAudiencePrefillStates.clear();
                    customAudiencePrefillStates.add(loadJSON(json, context));
                    return customAudiencePrefillStates;
                }
            }
        } catch (Exception e) {
            exception = e;
        }
        throw new MalformedResponseException(
                "Invalid response string: " + json,
                exception
        );
    }

    @Override
    public APIContext getContext() {
        return context;
    }

    @Override
    public void setContext(APIContext context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return getGson().toJson(this);
    }


    public String getFieldDescription() {
        return mDescription;
    }

    public CustomAudiencePrefillState setFieldDescription(String value) {
        this.mDescription = value;
        return this;
    }

    public Long getFieldNumAdded() {
        return mNumAdded;
    }

    public CustomAudiencePrefillState setFieldNumAdded(Long value) {
        this.mNumAdded = value;
        return this;
    }

    public String getFieldStatus() {
        return mStatus;
    }

    public CustomAudiencePrefillState setFieldStatus(String value) {
        this.mStatus = value;
        return this;
    }


    synchronized /*package*/ static Gson getGson() {
        if (gson != null) {
            return gson;
        } else {
            gson = new GsonBuilder()
                    .excludeFieldsWithModifiers(Modifier.STATIC)
                    .excludeFieldsWithModifiers(Modifier.PROTECTED)
                    .disableHtmlEscaping()
                    .create();
        }
        return gson;
    }

    public CustomAudiencePrefillState copyFrom(CustomAudiencePrefillState instance) {
        this.mDescription = instance.mDescription;
        this.mNumAdded = instance.mNumAdded;
        this.mStatus = instance.mStatus;
        this.context = instance.context;
        this.rawValue = instance.rawValue;
        return this;
    }

    public static APIRequest.ResponseParser<CustomAudiencePrefillState> getParser() {
        return new APIRequest.ResponseParser<CustomAudiencePrefillState>() {
            public APINodeList<CustomAudiencePrefillState> parseResponse(String response, APIContext context, APIRequest<CustomAudiencePrefillState> request) throws MalformedResponseException {
                return CustomAudiencePrefillState.parseResponse(response, context, request);
            }
        };
    }
}
