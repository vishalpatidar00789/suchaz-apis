/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { VendorSuchazComponent } from '../../../../../../main/webapp/app/entities/vendor-suchaz/vendor-suchaz.component';
import { VendorSuchazService } from '../../../../../../main/webapp/app/entities/vendor-suchaz/vendor-suchaz.service';
import { VendorSuchaz } from '../../../../../../main/webapp/app/entities/vendor-suchaz/vendor-suchaz.model';

describe('Component Tests', () => {

    describe('VendorSuchaz Management Component', () => {
        let comp: VendorSuchazComponent;
        let fixture: ComponentFixture<VendorSuchazComponent>;
        let service: VendorSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [VendorSuchazComponent],
                providers: [
                    VendorSuchazService
                ]
            })
            .overrideTemplate(VendorSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(VendorSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VendorSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new VendorSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.vendors[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
