import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { UserProfileSuchaz } from './user-profile-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<UserProfileSuchaz>;

@Injectable()
export class UserProfileSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/user-profiles';

    constructor(private http: HttpClient) { }

    create(userProfile: UserProfileSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(userProfile);
        return this.http.post<UserProfileSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(userProfile: UserProfileSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(userProfile);
        return this.http.put<UserProfileSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<UserProfileSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<UserProfileSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<UserProfileSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<UserProfileSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: UserProfileSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<UserProfileSuchaz[]>): HttpResponse<UserProfileSuchaz[]> {
        const jsonResponse: UserProfileSuchaz[] = res.body;
        const body: UserProfileSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to UserProfileSuchaz.
     */
    private convertItemFromServer(userProfile: UserProfileSuchaz): UserProfileSuchaz {
        const copy: UserProfileSuchaz = Object.assign({}, userProfile);
        return copy;
    }

    /**
     * Convert a UserProfileSuchaz to a JSON which can be sent to the server.
     */
    private convert(userProfile: UserProfileSuchaz): UserProfileSuchaz {
        const copy: UserProfileSuchaz = Object.assign({}, userProfile);
        return copy;
    }
}
