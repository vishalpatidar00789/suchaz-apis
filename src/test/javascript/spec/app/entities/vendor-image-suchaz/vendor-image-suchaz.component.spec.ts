/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { VendorImageSuchazComponent } from '../../../../../../main/webapp/app/entities/vendor-image-suchaz/vendor-image-suchaz.component';
import { VendorImageSuchazService } from '../../../../../../main/webapp/app/entities/vendor-image-suchaz/vendor-image-suchaz.service';
import { VendorImageSuchaz } from '../../../../../../main/webapp/app/entities/vendor-image-suchaz/vendor-image-suchaz.model';

describe('Component Tests', () => {

    describe('VendorImageSuchaz Management Component', () => {
        let comp: VendorImageSuchazComponent;
        let fixture: ComponentFixture<VendorImageSuchazComponent>;
        let service: VendorImageSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [VendorImageSuchazComponent],
                providers: [
                    VendorImageSuchazService
                ]
            })
            .overrideTemplate(VendorImageSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(VendorImageSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VendorImageSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new VendorImageSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.vendorImages[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
