/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { UserGiftWrapperSuchazComponent } from '../../../../../../main/webapp/app/entities/user-gift-wrapper-suchaz/user-gift-wrapper-suchaz.component';
import { UserGiftWrapperSuchazService } from '../../../../../../main/webapp/app/entities/user-gift-wrapper-suchaz/user-gift-wrapper-suchaz.service';
import { UserGiftWrapperSuchaz } from '../../../../../../main/webapp/app/entities/user-gift-wrapper-suchaz/user-gift-wrapper-suchaz.model';

describe('Component Tests', () => {

    describe('UserGiftWrapperSuchaz Management Component', () => {
        let comp: UserGiftWrapperSuchazComponent;
        let fixture: ComponentFixture<UserGiftWrapperSuchazComponent>;
        let service: UserGiftWrapperSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [UserGiftWrapperSuchazComponent],
                providers: [
                    UserGiftWrapperSuchazService
                ]
            })
            .overrideTemplate(UserGiftWrapperSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UserGiftWrapperSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserGiftWrapperSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new UserGiftWrapperSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.userGiftWrappers[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
