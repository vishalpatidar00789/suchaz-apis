/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { SuchAzUserSuchazComponent } from '../../../../../../main/webapp/app/entities/such-az-user-suchaz/such-az-user-suchaz.component';
import { SuchAzUserSuchazService } from '../../../../../../main/webapp/app/entities/such-az-user-suchaz/such-az-user-suchaz.service';
import { SuchAzUserSuchaz } from '../../../../../../main/webapp/app/entities/such-az-user-suchaz/such-az-user-suchaz.model';

describe('Component Tests', () => {

    describe('SuchAzUserSuchaz Management Component', () => {
        let comp: SuchAzUserSuchazComponent;
        let fixture: ComponentFixture<SuchAzUserSuchazComponent>;
        let service: SuchAzUserSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [SuchAzUserSuchazComponent],
                providers: [
                    SuchAzUserSuchazService
                ]
            })
            .overrideTemplate(SuchAzUserSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SuchAzUserSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SuchAzUserSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new SuchAzUserSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.suchAzUsers[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
