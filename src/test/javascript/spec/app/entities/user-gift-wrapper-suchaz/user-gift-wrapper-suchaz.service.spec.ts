/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { JhiDateUtils } from 'ng-jhipster';

import { UserGiftWrapperSuchazService } from '../../../../../../main/webapp/app/entities/user-gift-wrapper-suchaz/user-gift-wrapper-suchaz.service';
import { SERVER_API_URL } from '../../../../../../main/webapp/app/app.constants';

describe('Service Tests', () => {

    describe('UserGiftWrapperSuchaz Service', () => {
        let injector: TestBed;
        let service: UserGiftWrapperSuchazService;
        let httpMock: HttpTestingController;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [
                    HttpClientTestingModule
                ],
                providers: [
                    JhiDateUtils,
                    UserGiftWrapperSuchazService
                ]
            });
            injector = getTestBed();
            service = injector.get(UserGiftWrapperSuchazService);
            httpMock = injector.get(HttpTestingController);
        });

        describe('Service methods', () => {
            it('should call correct URL', () => {
                service.find(123).subscribe(() => {});

                const req  = httpMock.expectOne({ method: 'GET' });

                const resourceUrl = SERVER_API_URL + 'api/user-gift-wrappers';
                expect(req.request.url).toEqual(resourceUrl + '/' + 123);
            });
            it('should return UserGiftWrapperSuchaz', () => {

                service.find(123).subscribe((received) => {
                    expect(received.body.id).toEqual(123);
                });

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush({id: 123});
            });

            it('should propagate not found response', () => {

                service.find(123).subscribe(null, (_error: any) => {
                    expect(_error.status).toEqual(404);
                });

                const req  = httpMock.expectOne({ method: 'GET' });
                req.flush('Invalid request parameters', {
                    status: 404, statusText: 'Bad Request'
                });

            });
        });

        afterEach(() => {
            httpMock.verify();
        });

    });

});
