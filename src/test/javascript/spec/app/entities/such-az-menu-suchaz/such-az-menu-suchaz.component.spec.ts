/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { SuchAzMenuSuchazComponent } from '../../../../../../main/webapp/app/entities/such-az-menu-suchaz/such-az-menu-suchaz.component';
import { SuchAzMenuSuchazService } from '../../../../../../main/webapp/app/entities/such-az-menu-suchaz/such-az-menu-suchaz.service';
import { SuchAzMenuSuchaz } from '../../../../../../main/webapp/app/entities/such-az-menu-suchaz/such-az-menu-suchaz.model';

describe('Component Tests', () => {

    describe('SuchAzMenuSuchaz Management Component', () => {
        let comp: SuchAzMenuSuchazComponent;
        let fixture: ComponentFixture<SuchAzMenuSuchazComponent>;
        let service: SuchAzMenuSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [SuchAzMenuSuchazComponent],
                providers: [
                    SuchAzMenuSuchazService
                ]
            })
            .overrideTemplate(SuchAzMenuSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SuchAzMenuSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SuchAzMenuSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new SuchAzMenuSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.suchAzMenus[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
