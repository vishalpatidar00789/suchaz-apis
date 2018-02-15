/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { StoreSuchazComponent } from '../../../../../../main/webapp/app/entities/store-suchaz/store-suchaz.component';
import { StoreSuchazService } from '../../../../../../main/webapp/app/entities/store-suchaz/store-suchaz.service';
import { StoreSuchaz } from '../../../../../../main/webapp/app/entities/store-suchaz/store-suchaz.model';

describe('Component Tests', () => {

    describe('StoreSuchaz Management Component', () => {
        let comp: StoreSuchazComponent;
        let fixture: ComponentFixture<StoreSuchazComponent>;
        let service: StoreSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [StoreSuchazComponent],
                providers: [
                    StoreSuchazService
                ]
            })
            .overrideTemplate(StoreSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(StoreSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StoreSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new StoreSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.stores[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
